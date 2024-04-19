package com.daily.allowance.domain.payment.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.vo.MissionResponseVo;
import com.daily.allowance.domain.payment.converter.PaymentConverter;
import com.daily.allowance.domain.payment.converter.PaymentHistoryConverter;
import com.daily.allowance.domain.payment.dto.request.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.dto.response.PaymentBenefitResponseDto;
import com.daily.allowance.domain.payment.dto.response.PaymentResponseDto;
import com.daily.allowance.domain.payment.exception.PaymentException;
import com.daily.allowance.domain.payment.model.PaymentStatus;
import com.daily.allowance.domain.payment.service.PaymentService;
import com.daily.allowance.domain.payment.vaildator.PaymentValidator;
import com.daily.allowance.domain.payment.vo.PaymentSearchResponseVo;

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class PaymentBusiness {

	private final PaymentService paymentService;
	private final PaymentConverter paymentConverter;
	private final PaymentHistoryConverter paymentHistoryConverter;
	private final MissionBusiness missionBusiness;
	private final PaymentValidator paymentValidator;

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	public PaymentBenefitResponseDto searchPaymentBenefitMonthly(PaymentSearchRequestDto request) {
		List<PaymentSearchResponseVo> paymentSearchResponseVo = paymentService.searchPaymentBenefitMonthly(request);
		return paymentConverter.toResponseBenefitMonthly(paymentSearchResponseVo);
	}

	/**
	 * TODO 데일리 용돈 받기, 미션별 미션은 하루에 한번만 가능하다.
	 * 고민 : 여기서 중복 참여 건에 대해서 문제는 없으나, 실제로 지급 금액에 문제가 있어 한번 지급 실패된 케이스에 대해서는 어떻게 해야할지 고민이다.
	 *
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
		paymentValidator.duplicatePaymentWithThrow(paymentRequestDto);

		// 2. 데일리 용돈 지급 이력 등록 및 히스토리 등록
		registerPaymentAndHistory(paymentRequestDto);

		// 3. 지급 금액 검증
		paymentValidator.amountLessThanZeroWithThrow(paymentRequestDto);

		// 4. 수신모듈 호출
		// 수신모듈 호출 부분
		if (!receiveModuleCall()) {
			// 수신모듈 에러
		}
		// 4-1. 정상 응답

		// 5. 지급 완료 처리
		PaymentResponseDto response = completedPayment(paymentRequestDto);
		return response;
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
		paymentValidator.duplicatePaymentWithThrow(paymentRequestDto);

		// 2. 미션 지급 이력 등록 및 히스토리 등록
		registerPaymentAndHistory(paymentRequestDto);

		// 3. 미션 ID 를 기반으로 미션 정보 가져오기.
		MissionResponseVo missionResponseVo = missionBusiness.searchMissionDetailById(request.getMissionId());

		// 3-1. 미션 운영 기간 및 미션 금액 검증
		paymentValidator.validateMissionWithThrow(paymentRequestDto, missionResponseVo);

		// 4. 수신모듈 호출
		// 수신모듈 호출 부분
		if (!receiveModuleCall()) {
			// 수신모듈 에러
		}
		// 4-1. 정상 응답

		// 5. 지급 완료 처리
		PaymentResponseDto response = completedPayment(paymentRequestDto);
		response.setMissionName(missionResponseVo.getMissionName());

		return response;

	}

	/**
	 * [ payment ] - 지급 완료 처리
	 */
	private PaymentResponseDto completedPayment(PaymentRequestDto request) {
		request.setStatus(PaymentStatus.SUCCESS);
		paymentService.modifiedPayment(request);

		return paymentConverter.toResponse(request);
	}

	/**
	 * [ payment ] - 지급 이력 등록 및 히스토리 등록
	 */
	private void registerPaymentAndHistory(PaymentRequestDto paymentRequestDto) {
		// 1. 지급 이력 등록
		paymentService.registerPayment(paymentRequestDto);

		// 2. 히스토리 등록
		registerHistory(paymentHistoryConverter.toHistoryDto(paymentRequestDto));
	}

	/**
	 * [ payment ] - 히스토리 등록
	 */
	private void registerHistory(PaymentHistoryRequestDto paymentHistoryDto) {
		// 1. 히스토리 등록
		paymentService.registerPaymentHistory(paymentHistoryDto);
	}

	/**
	 * [ payment ] - 지급 실패 히스토리 등록
	 */
	public void registerHistoryWithThrow(PaymentHistoryRequestDto paymentHistoryDto, CodeIfs errorCode) {
		// 1. 히스토리 등록
		registerHistory(paymentHistoryDto);

		throw new PaymentException(errorCode);
	}

	/**
	 * [ payment ] - 수신모듈 호출
	 */
	private boolean receiveModuleCall() {
		// 수신모듈 호출 부분
		return true;
	}
}
