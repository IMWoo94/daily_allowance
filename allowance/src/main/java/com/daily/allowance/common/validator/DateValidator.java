package com.daily.allowance.common.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.exception.DateTypeInvalidException;

public class DateValidator {

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 유효한 날짜 검증
	 */
	public static void dateFormatValidateWithThrow(LocalDate... date) {
		try {
			for (LocalDate value : date) {
				simpleDateFormat.setLenient(false);
				simpleDateFormat.parse(String.valueOf(value));
			}
		} catch (ParseException e) {
			throw new DateTypeInvalidException(ErrorCode.INVALID_DATE, e);
		}
	}

	/**
	 * 시작일, 종료일 크기 비교
	 */
	public static void compareDateWithThrow(LocalDate sDate, LocalDate eDate) {
		int compare = sDate.compareTo(eDate);

		// 종료일이 시작일 보다 작은 경우 예외 처리
		if (compare > 0) {
			throw new DateTypeInvalidException(ErrorCode.END_DATE_SMALL);
		}
	}
}
