package com.daily.allowance.domain.payment.vaildator;

import org.springframework.stereotype.Component;

import com.daily.allowance.common.code.CodeIfs;
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
	 * 1. 미션 운영 기간 검증
	 * 2. 미션 금액 검증
	 */
	public void validateMissionWithThrow(PaymentRequestDto request,
		MissionResponseVo missionResponseVo) {
		// 1. 미션 운영 기간 검증
		missionPeriodCheck(request, missionResponseVo);

		// 2. 미션 금액 검증
		missionAmountCheck(request, missionResponseVo);
	}

	/**
	 * [ payment ] - 미션 운영 기간 검증
	 */
	private void missionPeriodCheck(PaymentRequestDto request,
		MissionResponseVo missionResponseVo) {

		// 2. 미션 운영 기간 확인
		if (!missionResponseVo.operatedPeriodCheck(request.getPaymentDate())) {
			// 지급 불가 - 미션 미운영 기간
			registerHistoryWithThrow(paymentHistoryConverter.toHistoryDto(request, ReasonStatus.NON_OPERATING_PERIOD),
				ErrorCode.NON_OPERATING_PERIOD);
		}
	}

	/**
	 * [ payment ] - 미션 금액 검증
	 */
	private void missionAmountCheck(PaymentRequestDto request,
		MissionResponseVo missionResponseVo) {

		// 3. 미션 금액과 지급 금액 확인
		if (!missionResponseVo.amountCheck(request.getPaymentAmount())) {
			// 미션 지급 금액 불일치 예외 처리 및 지급 실패
			// 지급 불가 - 미션 지급 금액 불일치
			registerHistoryWithThrow(paymentHistoryConverter.toHistoryDto(request, ReasonStatus.AMOUNT_NOT_MATCH),
				ErrorCode.AMOUNT_NOT_MATCH);
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
			registerHistoryWithThrow(paymentHistoryConverter.toHistoryDto(request, ReasonStatus.AMOUNT_LESS_THAN_ZERO),
				ErrorCode.AMOUNT_LESS_THAN_ZERO);
		}
	}

	/**
	 * [ payment ] - 중복 참여 검증
	 */
	public void duplicatePaymentWithThrow(PaymentRequestDto request) {

		PaymentResponseDto duplicatePayment = paymentService.searchDuplicatePayment(request);
		if (duplicatePayment != null) {
			PaymentHistoryRequestDto paymentHistoryDto = paymentHistoryConverter.toHistoryDto(request);
			// 지급 ID 추가
			paymentHistoryDto.setPaymentId(duplicatePayment.getPaymentId());
			// 지급 불가 - 중복 참여
			// 중복 참여 - 기지급
			if (duplicatePayment.getStatus().equals(PaymentStatus.SUCCESS)) {
				// 기지급
				paymentHistoryDto.setReason(ReasonStatus.ALREADY_PAYMENT);
				registerHistoryWithThrow(paymentHistoryDto, ErrorCode.ALREADY_PAYMENT);
			} else {
				// 중복 참여
				paymentHistoryDto.setReason(ReasonStatus.DUPLICATE_PARTICIPATION);
				registerHistoryWithThrow(paymentHistoryDto, ErrorCode.DUPLICATED_PARTICIPATE);
			}
		}
	}

	/**
	 * [ payment ] - 지급 실패 히스토리 등록
	 */
	public void registerHistoryWithThrow(PaymentHistoryRequestDto paymentHistoryDto, CodeIfs errorCode) {
		// 1. 히스토리 등록
		paymentService.registerPaymentHistory(paymentHistoryDto);

		throw new PaymentException(errorCode);
	}

}
