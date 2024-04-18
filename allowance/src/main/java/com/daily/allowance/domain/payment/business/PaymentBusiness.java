package com.daily.allowance.domain.payment.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.payment.converter.PaymentConverter;
import com.daily.allowance.domain.payment.converter.PaymentHistoryConverter;
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

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class PaymentBusiness {

	private final PaymentService paymentService;
	private final PaymentConverter paymentConverter;
	private final PaymentHistoryConverter paymentHistoryConverter;

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	public List<PaymentResponseDto> searchPayment(PaymentSearchRequestDto request) {
		return paymentService.searchPayment(request);
	}

	/**
	 * [ payment ] - 데일리 용돈 받기
	 *
	 * 1. 데일리 용돈 받기 중복 확인 ( throw exception )
	 * 2. 지급 이력 등록 및 히스토리 등록
	 * 3. 지급 처리 하기 전에 금액 검증  ( throw exception )
	 * 4. 수신모듈 호출
	 * 5. 지급 완료 처리
	 */
	public PaymentResponseDto dailyAllowancePayment(Member member, PaymentDailyRequestDto request) {
		PaymentRequestDto paymentRequestDto = paymentConverter.toPaymentDtoByDaily(member, request);

		// 1. 데일리 용돈 받기 중복 확인
		duplicatePaymentWithThrow(paymentRequestDto);

		// 2. 지급 이력 등록 및 히스토리 등록
		registerPaymentAndHistory(paymentRequestDto);

		// 3. 지급 처리 하기 전에 금액 검증
		amountLessThanZeroWithThrow(paymentRequestDto);

		// 4. 수신모듈 호출
		// 수신모듈 호출 부분
		// 4-1. 정상 응답

		// 5. 지급 완료 처리
		PaymentResponseDto response = completedPayment(paymentRequestDto);
		return response;
	}

	public PaymentResponseDto completedPayment(PaymentRequestDto request) {
		request.setStatus(PaymentStatus.SUCCESS);
		paymentService.modifiedPayment(request);

		PaymentResponseDto paymentResponseDto = paymentConverter.toResponseByDaily(request);
		return paymentResponseDto;
	}

	/**
	 * [ payment ] - 지급 이력 등록 및 히스토리 등록
	 */
	private void registerPaymentAndHistory(PaymentRequestDto paymentRequestDto) {
		// 1. 지급 이력 등록
		paymentService.registerPayment(paymentRequestDto);

		// 2. 히스토리 dto 생성
		PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(paymentRequestDto);

		// 3. 히스토리 등록
		paymentService.registerPaymentHistory(paymentHistoryDto);
	}

	/**
	 * 데일리 용돈 금액 검증
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
	 * 중복 참여 검증
	 */
	private void duplicatePaymentWithThrow(PaymentRequestDto request) {
		PaymentResponseDto result = paymentService.searchDuplicatePayment(request);
		if (result != null) {
			// 지급 불가 - 중복 참여
			// 1. 지급 실패 history 등록
			PaymentHistoryDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request, ReasonStatus.DUPLICATE);
			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.DUPLICATED_PARTICIPATE, "데일리 용돈 받기 중복 참여");
		}
	}

	/**
	 * [ payment ] - 미션 도전
	 * 미션 검증 필요
	 * 1. 미션 기간 및 운영 상태 확인
	 *
	 * 지급 검증 필요
	 * 1. 금액 ( 미션 금액과 지급 금액 확인 )
	 * 2. 미션 중복 지급 여부 확인
	 *
	 * 지급 처리 - Payment 영역
	 * 1. 지급 내역 등록 및 히스토리 등록
	 * 2. 혜택 지급 - 입금 모듈 호출 ( 주석으로 표시 )
	 * 3. 지급 완료 처리
	 */
	public void missionPayment(Member member, PaymentMissionRequestDto request) {
		PaymentRequestDto paymentRequestDto = paymentConverter.toPaymentDtoByMission(member, request);
		// 미션 도전

		// TODO
		/**
		 * 미션 검증 필요
		 * 1. 미션 기간 및 운영 상태 확인
		 */

		// TODO
		/**
		 * 지급 검증 필요
		 * 1. 금액 ( 미션 금액과 지급 금액 확인 )
		 * 2. 미션 중복 지급 여부 확인
		 *
		 * 지급 처리 - Payment 영역
		 * 1. 지급 내역 등록 및 히스토리 등록
		 * 2. 혜택 지급 - 입금 모듈 호출 ( 주석으로 표시 )
		 * 3. 지급 완료 처리
		 */

	}
}
