package com.daily.allowance.domain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentStatus {

	SUCCESS("지급 완료"),
	FAIL("지급 실패");

	private final String description;
}
