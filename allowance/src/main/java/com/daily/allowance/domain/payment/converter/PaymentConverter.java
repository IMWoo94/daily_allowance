package com.daily.allowance.domain.payment.converter;

import java.time.LocalDate;
import java.util.List;

import com.daily.allowance.common.annotation.Converter;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.payment.dto.request.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentRequest;
import com.daily.allowance.domain.payment.dto.request.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.response.PaymentBenefitResponseDto;
import com.daily.allowance.domain.payment.dto.response.PaymentResponseDto;
import com.daily.allowance.domain.payment.model.PaymentCode;
import com.daily.allowance.domain.payment.model.PaymentStatus;
import com.daily.allowance.domain.payment.vo.PaymentSearchResponseVo;

@Converter
public class PaymentConverter {

	public PaymentRequestDto toPaymentDto(Member member, PaymentRequest paymentRequest) {
		if (paymentRequest instanceof PaymentDailyRequestDto) {
			return toPaymentDtoByDaily(member, (PaymentDailyRequestDto)paymentRequest);
		} else if (paymentRequest instanceof PaymentMissionRequestDto) {
			return toPaymentDtoByMission(member, (PaymentMissionRequestDto)paymentRequest);
		}
		return null;
	}

	public PaymentRequestDto toPaymentDtoByDaily(Member member, PaymentDailyRequestDto request) {
		return PaymentRequestDto.builder()
			.memberId(member.getMemberId())
			.paymentAmount(request.getPaymentAmount())
			.paymentCode(PaymentCode.DAILY)
			.status(PaymentStatus.FAIL)
			.paymentDate(LocalDate.now())
			.build();
	}

	public PaymentRequestDto toPaymentDtoByMission(Member member, PaymentMissionRequestDto request) {
		return PaymentRequestDto.builder()
			.memberId(member.getMemberId())
			.missionId(request.getMissionId())
			.paymentAmount(request.getMissionAmount())
			.paymentCode(PaymentCode.MISSION)
			.status(PaymentStatus.FAIL)
			.paymentDate(LocalDate.now())
			.build();
	}

	public PaymentResponseDto toResponse(PaymentRequestDto request) {
		return PaymentResponseDto.builder()
			.paymentId(request.getPaymentId())
			.memberId(request.getMemberId())
			.missionId(request.getMissionId())
			.paymentCode(request.getPaymentCode())
			.paymentAmount(request.getPaymentAmount())
			.status(request.getStatus())
			.paymentDate(request.getPaymentDate())
			.missionName(request.getPaymentCode().getDescription())
			.build();
	}

	public PaymentResponseDto toResponse(PaymentRequestDto request, String missionName) {
		return PaymentResponseDto.builder()
			.paymentId(request.getPaymentId())
			.memberId(request.getMemberId())
			.missionId(request.getMissionId())
			.paymentCode(request.getPaymentCode())
			.paymentAmount(request.getPaymentAmount())
			.status(request.getStatus())
			.paymentDate(request.getPaymentDate())
			.missionName(missionName)
			.build();
	}

	public PaymentBenefitResponseDto toResponseBenefitMonthly(List<PaymentSearchResponseVo> response) {
		int totalAmount = response.stream()
			.map(PaymentSearchResponseVo::getPaymentAmount)
			.mapToInt(Integer::intValue)
			.sum();

		return PaymentBenefitResponseDto.builder()
			.results(response)
			.totalAmount(totalAmount)
			.build();
	}
}
