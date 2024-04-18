package com.daily.allowance.domain.payment.converter;

import java.time.LocalDate;
import java.util.Optional;

import com.daily.allowance.common.annotation.Converter;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.domain.payment.dto.request.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentRequestDto;
import com.daily.allowance.domain.payment.exception.PaymentException;
import com.daily.allowance.domain.payment.model.ReasonStatus;

@Converter
public class PaymentHistoryConverter {

	public PaymentHistoryRequestDto toHistoryDto(PaymentRequestDto request) {
		return Optional.ofNullable(request)
			.map(it -> {
				return PaymentHistoryRequestDto.builder()
					.paymentId(it.getPaymentId())
					.memberId(it.getMemberId())
					.missionId(it.getMissionId())
					.paymentCode(it.getPaymentCode())
					.paymentAmount(it.getPaymentAmount())
					.paymentDate(it.getPaymentDate())
					.createdDate(LocalDate.now())
					.build();
			})
			.orElseThrow(() -> new PaymentException(ErrorCode.NULL_POINT));
	}

	public PaymentHistoryRequestDto toHistoryDto(PaymentRequestDto request, ReasonStatus reasonStatus) {
		return Optional.ofNullable(request)
			.map(it -> {
				return PaymentHistoryRequestDto.builder()
					.paymentId(it.getPaymentId())
					.memberId(it.getMemberId())
					.missionId(it.getMissionId())
					.paymentCode(it.getPaymentCode())
					.paymentAmount(it.getPaymentAmount())
					.paymentDate(it.getPaymentDate())
					.createdDate(LocalDate.now())
					.reason(reasonStatus)
					.build();
			})
			.orElseThrow(() -> new PaymentException(ErrorCode.NULL_POINT));
	}

}
