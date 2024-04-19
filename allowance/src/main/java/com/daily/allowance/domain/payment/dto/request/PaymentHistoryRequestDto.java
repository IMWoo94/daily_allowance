package com.daily.allowance.domain.payment.dto.request;

import java.time.LocalDate;

import com.daily.allowance.domain.payment.model.PaymentCode;
import com.daily.allowance.domain.payment.model.ReasonStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentHistoryRequestDto {

	private Long paymentHistoryId;
	private Long paymentId;
	private Long memberId;
	private Long missionId;
	private PaymentCode paymentCode;
	private Integer paymentAmount;
	private LocalDate paymentDate;
	private ReasonStatus reason;
	private String error;
	private LocalDate createdDate;
}
