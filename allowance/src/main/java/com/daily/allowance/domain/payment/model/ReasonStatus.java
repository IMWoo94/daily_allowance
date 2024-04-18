package com.daily.allowance.domain.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReasonStatus {

	DUPLICATE("중복 지급"),
	NON_OPERATING_PERIOD("미션 미운영 기간"),
	AMOUNT_NOT_MATCH("금액 불일치"),
	AMOUNT_LESS_THAN_ZERO("금액 0원 이하");

	private final String description;
}
