package com.daily.allowance.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 정상 작동 코드의 경우 10000 번대 코드 사용
 */
@AllArgsConstructor
@Getter
public enum SuccessCode implements CodeIfs {

	OK(HttpStatus.OK.value(), 10000, "OK");
	private final Integer httpStatusCode;
	// 정의한 status 코드
	private final Integer responseCode;
	private final String description;
}