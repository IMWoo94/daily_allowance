package com.daily.allowance.domain.payment.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.vo.MissionResponseVo;
import com.daily.allowance.domain.payment.converter.PaymentConverter;
import com.daily.allowance.domain.payment.converter.PaymentHistoryConverter;
import com.daily.allowance.domain.payment.dto.PaymentBenefitResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentHistoryDto;
import com.daily.allowance.domain.payment.dto.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.exception.PaymentException;
import com.daily.allowance.domain.payment.model.PaymentStatus;
import com.daily.allowance.domain.payment.model.ReasonStatus;
import com.daily.allowance.domain.payment.service.PaymentService;
import com.daily.allowance.domain.payment.vo.PaymentSearchResponseVo;

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class PaymentBusiness {

	private final PaymentService paymentService;
	private final PaymentConverter paymentConverter;
	private final PaymentHistoryConverter paymentHistoryConverter;
	private final MissionBusiness missionBusiness;

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	public PaymentBenefitResponseDto searchPaymentBenefitMonthly(PaymentSearchRequestDto request) {
		List<PaymentSearchResponseVo> paymentSearchResponseVo = paymentService.searchPaymentBenefitMonthly(request);
		return paymentConverter.toResponseBenefitMonthly(paymentSearchResponseVo);
	}

	/**
	 * TODO 데일리 용돈 받기, 미션은 하루에 한번만 가능하다.
	 * 고민 : 여기서 중복 참여 건에 대해서 문제는 없으나, 실제로 지급 금액에 문제가 있어 한번 지급 실패된 케이스에 대해서는 어떻게 해야할지 고민이다.
	 * 해결 : 배치 프로그램을 통해 실제 지급 내역의 금액을 재수정 하고 지급 처리
	 * - 데일리 용돈 받기 : 랜덤의 숫자를 다시 제공 ( 서버에서 넘어오는 값으로 사실상 일어나기 어렵다고 볼 수 있지만, 그런 경우 랜덤 로직은 동일하게 적용하여 지급 )
	 * - 미션 : 진행 중이 미션의 금액을 확인하여 지급 처리 ( 미션 정보의 경우 이를 위해서 변경은 활성화, 비활성화만 진행 되도록 반영 )
	 */

	/**
	 * [ payment ] - 데일리 용돈 받기
	 *
	 * 1. 데일리 용돈 받기 중복 확인 ( throw exception )
	 * 2. 데일리 용돈 지급 이력 등록 및 히스토리 등록
	 * 3. 지급 금액 검증  ( throw exception )
	 * 4. 수신모듈 호출
	 * 5. 지급 완료 처리
	 */
	public PaymentResponseDto dailyAllowancePayment(Member member, PaymentDailyRequestDto request) {
		PaymentRequestDto paymentRequestDto = paymentConverter.toPaymentDtoByDaily(member, request);

		// 1. 데일리 용돈 받기 중복 확인 - 중복 참여는 예외 발생
		duplicatePaymentWithThrow(paymentRequestDto);

		// 2. 데일리 용돈 지급 이력 등록 및 히스토리 등록
		registerPaymentAndHistory(paymentRequestDto);

		// 3. 지급 금액 검증
		amountLessThanZeroWithThrow(paymentRequestDto);

		// 4. 수신모듈 호출
		// 수신모듈 호출 부분
		if (!receiveModuleCall()) {
			// 수신모듈 에러
		}
		// 4-1. 정상 응답

		// 5. 지급 완료 처리
		return completedPayment(paymentRequestDto);
	}

	/**
	 * [ payment ] - 미션 지급
	 *
	 * 1. 미션 중복 확인 ( throw exception )
	 * 2. 미션 지급 이력 등록 및 히스토리 등록
	 * 3. 미션 운영 기간 및 미션 금액 검증 ( throw exception )
	 * 4. 수신모듈 호출
	 * 5. 지급 완료 처리
	 */
	public PaymentResponseDto missionPayment(Member member, PaymentMissionRequestDto request) {
		PaymentRequestDto paymentRequestDto = paymentConverter.toPaymentDtoByMission(member, request);

		// 1. 미션 중복 확인 - 중복 참여는 예외 발생
		duplicatePaymentWithThrow(paymentRequestDto);

		// 2. 미션 지급 이력 등록 및 히스토리 등록
		registerPaymentAndHistory(paymentRequestDto);

		// 3. 미션 ID 를 기반으로 미션 정보 가져오기.
		MissionResponseVo missionResponseVo = missionBusiness.searchMissionDetailById(request.getMissionId());

		// 3-1. 미션 운영 기간 및 미션 금액 검증
		missionPeriodAndAmountWithThrow(paymentRequestDto, missionResponseVo);

		// 4. 수신모듈 호출
		// 수신모듈 호출 부분
		if (!receiveModuleCall()) {
			// 수신모듈 에러
		}
		// 4-1. 정상 응답

		// 5. 지급 완료 처리
		return completedPayment(paymentRequestDto);

	}

	/**
	 * [ payment ] - 미션 운영 기간 및 미션 금액 검증
	 */
	private void missionPeriodAndAmountWithThrow(PaymentRequestDto request, MissionResponseVo missionResponseVo) {

		// 2. 미션 운영 기간 확인
		if (!missionResponseVo.operatedPeriodCheck(request.getPaymentDate())) {
			// 지급 불가 - 미션 미운영 기간
			PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.NON_OPERATING_PERIOD);
			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.NON_OPERATING_PERIOD);
		}

		// 3. 미션 금액과 지급 금액 확인
		if (!missionResponseVo.amountCheck(request.getPaymentAmount())) {
			// 미션 지급 금액 불일치 예외 처리 및 지급 실패
			// 지급 불가 - 미션 지급 금액 불일치
			PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.AMOUNT_NOT_MATCH);
			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.AMOUNT_NOT_MATCH);
		}
	}

	/**
	 * [ payment ] - 지급 완료 처리
	 */
	private PaymentResponseDto completedPayment(PaymentRequestDto request) {
		request.setStatus(PaymentStatus.SUCCESS);
		paymentService.modifiedPayment(request);

		return paymentConverter.toResponseByDaily(request);
	}

	/**
	 * [ payment ] - 지급 이력 등록 및 히스토리 등록
	 */
	// @Transactional
	protected void registerPaymentAndHistory(PaymentRequestDto paymentRequestDto) {
		// 1. 지급 이력 등록
		paymentService.registerPayment(paymentRequestDto);

		// 2. 히스토리 dto 생성
		PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(paymentRequestDto);

		// 3. 히스토리 등록
		paymentService.registerPaymentHistory(paymentHistoryDto);
	}

	/**
	 * [ payment ] - 데일리 용돈 금액 검증
	 */
	private void amountLessThanZeroWithThrow(PaymentRequestDto request) {
		boolean result = request.getPaymentAmount() <= 0;
		if (result) {
			// 지급 불가 - 금액 이슈
			// 1. 지급 실패 history 등록
			PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.AMOUNT_LESS_THAN_ZERO);

			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.AMOUNT_LESS_THAN_ZERO);
		}
	}

	/**
	 * [ payment ] - 중복 참여 검증
	 */
	private void duplicatePaymentWithThrow(PaymentRequestDto request) {
		PaymentResponseDto duplicatePayment = paymentService.searchDuplicatePayment(request);
		if (duplicatePayment != null) {
			PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.DUPLICATE_PARTICIPATION);
			// 지급 ID 추가
			paymentHistoryDto.setPaymentId(duplicatePayment.getPaymentId());
			// 지급 불가 - 중복 참여
			// 중복 참여 - 기지급
			if (duplicatePayment.getStatus().equals(PaymentStatus.SUCCESS)) {
				paymentHistoryDto.setReason(ReasonStatus.ALREADY_PAYMENT);
			}

			// 히스토리 등록
			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.DUPLICATED_PARTICIPATE);
		}
	}

	/**
	 * [ payment ] - 수신모듈 호출
	 */
	private boolean receiveModuleCall() {
		// 수신모듈 호출 부분
		return true;
	}
}
