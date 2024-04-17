package com.daily.allowance.domain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentCode {

	DAILY("데일리 용돈 받기"),
	MISSION("미션");

	private final String description;
}
