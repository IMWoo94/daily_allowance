package com.daily.allowance.domain.payment.vaildator;

import org.springframework.stereotype.Component;

import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.domain.mission.vo.MissionResponseVo;
import com.daily.allowance.domain.payment.converter.PaymentHistoryConverter;
import com.daily.allowance.domain.payment.dto.request.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.response.PaymentResponseDto;
import com.daily.allowance.domain.payment.exception.PaymentException;
import com.daily.allowance.domain.payment.model.PaymentStatus;
import com.daily.allowance.domain.payment.model.ReasonStatus;
import com.daily.allowance.domain.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentValidator {

	private final PaymentHistoryConverter paymentHistoryConverter;
	private final PaymentService paymentService;

	/**
	 * [ payment ] - 미션 운영 기간 및 미션 금액 검증
	 */
	public void missionPeriodAndAmountWithThrow(PaymentRequestDto request,
		MissionResponseVo missionResponseVo) {

		// 2. 미션 운영 기간 확인
		if (!missionResponseVo.operatedPeriodCheck(request.getPaymentDate())) {
			// 지급 불가 - 미션 미운영 기간
			PaymentHistoryRequestDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.NON_OPERATING_PERIOD);
			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.NON_OPERATING_PERIOD);
		}

		// 3. 미션 금액과 지급 금액 확인
		if (!missionResponseVo.amountCheck(request.getPaymentAmount())) {
			// 미션 지급 금액 불일치 예외 처리 및 지급 실패
			// 지급 불가 - 미션 지급 금액 불일치
			PaymentHistoryRequestDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.AMOUNT_NOT_MATCH);
			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.AMOUNT_NOT_MATCH);
		}
	}

	/**
	 * [ payment ] - 데일리 용돈 금액 검증
	 */
	public void amountLessThanZeroWithThrow(PaymentRequestDto request) {
		boolean result = request.getPaymentAmount() <= 0;
		if (result) {
			// 지급 불가 - 금액 이슈
			// 1. 지급 실패 history 등록
			PaymentHistoryRequestDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
				ReasonStatus.AMOUNT_LESS_THAN_ZERO);

			paymentService.registerPaymentHistory(paymentHistoryDto);

			throw new PaymentException(ErrorCode.AMOUNT_LESS_THAN_ZERO);
		}
	}

	/**
	 * [ payment ] - 중복 참여 검증
	 */
	public void duplicatePaymentWithThrow(PaymentRequestDto request) {
		PaymentResponseDto duplicatePayment = paymentService.searchDuplicatePayment(request);
		if (duplicatePayment != null) {
			PaymentHistoryRequestDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request,
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

}
