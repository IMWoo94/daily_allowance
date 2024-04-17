package com.daily.allowance.domain.payment.converter;

import java.time.LocalDate;

import com.daily.allowance.common.annotation.Converter;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.payment.dto.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentRequestDto;
import com.daily.allowance.domain.payment.model.PaymentCode;
import com.daily.allowance.domain.payment.model.PaymentStatus;

@Converter
public class PaymentConverter {

	public PaymentRequestDto toRequest(Member member, PaymentDailyRequestDto request) {
		return PaymentRequestDto.builder()
			.memberId(member.getMemberId())
			.paymentAmount(request.getPaymentAmount())
			.paymentCode(PaymentCode.DAILY)
			.status(PaymentStatus.WAIT)
			.paymentDate(LocalDate.now())
			.build();
	}

	public PaymentRequestDto toRequest(Member member, PaymentMissionRequestDto request) {
		return PaymentRequestDto.builder()
			.memberId(member.getMemberId())
			.missionId(request.getMissionId())
			.paymentAmount(request.getMissionAmount())
			.paymentCode(PaymentCode.MISSION)
			.status(PaymentStatus.WAIT)
			.paymentDate(LocalDate.now())
			.build();
	}
}
