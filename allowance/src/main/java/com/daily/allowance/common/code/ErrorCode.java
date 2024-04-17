package com.daily.allowance.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 시스템 Error 경우 30000 번대 코드 사용
 * 정해진 코드 이외 오류의 경우 99999 코드
 */
@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeIfs {

	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 30000, "잘못된 요청", "잘못된 요청"),
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30001, "서버 에러", "서버 에러"),
	NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30002, "Null Point", "Null Point"),

	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 99999, "예외 내역을 확인해주세요.", "정의되지 않은 예외 입니다.");
	private final Integer httpStatusCode;
	// 정의한 status 코드
	private final Integer responseCode;
	private final String description;
	private final String message;
}
