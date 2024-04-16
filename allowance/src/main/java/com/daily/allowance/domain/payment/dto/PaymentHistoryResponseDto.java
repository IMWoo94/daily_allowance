package com.daily.allowance.domain.payment.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentHistoryResponseDto {

	private Long paymentId;
	private int paymentAmount;
	private LocalDate paymentDate;
	private String status;
	private Long missionId;
	private String missionName;
}
