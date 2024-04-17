package com.daily.allowance.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	ADMIN("ADMIN"),
	USER("USER");

	private final String role;

}
