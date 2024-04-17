package com.daily.allowance.domain.payment.dto;

import java.time.LocalDate;

import com.daily.allowance.domain.payment.model.PaymentCode;
import com.daily.allowance.domain.payment.model.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDto {

	private Long paymentId;
	private Long memberId;
	private Long missionId;
	private PaymentCode paymentCode;
	private Integer paymentAmount;
	private PaymentStatus status;
	private LocalDate paymentDate;
	private String missionName;

}
