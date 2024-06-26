package com.daily.allowance.common.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Error 경우 30000 번대 코드 사용
 * 정해진 코드 이외 오류의 경우 99999 코드
 */
@AllArgsConstructor
@Getter
public enum ErrorCode implements CodeIfs {

	// 시스템 코드
	BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 30000, "잘못된 요청", "잘못된 요청"),
	SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30001, "서버 에러", "서버 에러"),
	NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30002, "Null Point", "Null Point"),

	// 미션 코드
	INVALID_DATE(HttpStatus.BAD_REQUEST.value(), 30003, "유효하지 않은 날짜 입니다.", "유효하지 않은 날짜 입니다."),
	END_DATE_SMALL(HttpStatus.BAD_REQUEST.value(), 30004, "종료일이 시작일보다 작습니다.", "운영 기간을 다시 확인해주세요."),
	INVALID_PERIOD(HttpStatus.BAD_REQUEST.value(), 30005, "운영 기간에 미션을 추가할 수 없습니다.", "운영 기간을 다시 확인해주세요."),
	MISSION_REGISTER_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30006, "미션 등록에 실패하였습니다.",
		"미션 등록에 실패하였습니다."),
	MISSION_MODIFIED_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30007, "미션 수정에 실패하였습니다.",
		"미션 수정에 실패하였습니다."),

	// 지급 코드
	DUPLICATED_PARTICIPATE(HttpStatus.BAD_REQUEST.value(), 30008, "중복 참여", "중복 참여"),
	AMOUNT_LESS_THAN_ZERO(HttpStatus.BAD_REQUEST.value(), 30009, "지급 금액이 0원 미만 입니다.", "지급 금액이 0원 미만 입니다."),
	NON_OPERATING_PERIOD(HttpStatus.BAD_REQUEST.value(), 30010, "미션 미운영 기간 입니다.", "미션 미운영 기간 입니다."),
	AMOUNT_NOT_MATCH(HttpStatus.BAD_REQUEST.value(), 30011, "지급 금액과 미션 금액이 일치하지 않습니다.", "지급 금액과 미션 금액이 일치하지 않습니다."),
	ALREADY_PAYMENT(HttpStatus.BAD_REQUEST.value(), 30012, "중복 지급", "중복 지급"),

	MISSION_VALIDATE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), 30013, "미션 정보가 확인되지 않습니다.", "미션 정보가 확인되지 않습니다."),
	// 규격 외 코드
	UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 99999, "예외 내역을 확인해주세요.", "정의되지 않은 예외 입니다.");
	private final Integer httpStatusCode;
	// 정의한 status 코드
	private final Integer responseCode;
	private final String description;
	private final String message;
}
