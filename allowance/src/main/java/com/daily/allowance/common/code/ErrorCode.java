package com.daily.allowance.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 시스템 Error 경우 30000 번대 코드 사용
 */
@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeIfs {

	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 30000, "잘못된 요청"),
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30001, "서버 에러"),
	NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30002, "Null Point");

	private final Integer httpStatusCode;
	// 정의한 status 코드
	private final Integer responseCode;
	private final String description;
}
