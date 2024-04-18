package com.daily.allowance.domain.payment.dto.response;

import java.time.LocalDate;

import com.daily.allowance.domain.payment.model.PaymentCode;
import com.daily.allowance.domain.payment.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
