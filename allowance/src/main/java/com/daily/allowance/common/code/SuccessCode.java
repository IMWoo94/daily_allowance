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

	// 시스템 코드
	OK(HttpStatus.OK.value(), 10000, "OK", "OK"),

	// 미션 코드
	MISSION_REGISTER_COMPLETE(HttpStatus.OK.value(), 10001, "미션 등록 완료", "미션 등록 완료"),
	MISSION_MODIFIED_COMPLETE(HttpStatus.OK.value(), 10002, "미션 수정 완료", "미션 수정 완료"),

	// 지급 코드
	SEARCH_PAYMENT_COMPLETE(HttpStatus.OK.value(), 10003, "지급 내역 조회 완료", "지급 내역 조회 완료"),
	DAILY_ALLOWANCE_PAYMENT_COMPLETE(HttpStatus.OK.value(), 10004, "데일리 용돈 지급 완료", "데일리 용돈 지급 완료"),
	MISSION_PAYMENT_COMPLETE(HttpStatus.OK.value(), 10005, "미션 지급 완료", "미션 지급 완료");

	private final Integer httpStatusCode;
	// 정의한 status 코드
	private final Integer responseCode;
	private final String description;
	private final String message;
}
