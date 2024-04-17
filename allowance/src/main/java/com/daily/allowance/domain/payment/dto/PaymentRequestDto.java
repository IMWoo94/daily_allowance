package com.daily.allowance.domain.payment.dto;

import java.time.LocalDate;

import com.daily.allowance.domain.payment.model.PaymentCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDto {

	private Long paymentId;
	private Long memberId;
	private Long missionId;
	private PaymentCode paymentCode;
	private Integer paymentAmount;
	private String status;
	private LocalDate paymentDate;
}
