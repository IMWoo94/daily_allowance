package com.daily.allowance.domain.mission.validator;

import java.time.LocalDate;

import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.domain.mission.exception.MissionRegisterException;

public class MissionValidator {

	/**
	 * 운영 기간 사전 등록 확인
	 */
	public static void periodValidateWithThrow(LocalDate sDate) {
		LocalDate current = LocalDate.now();
		int compare = sDate.compareTo(current);

		// 운영 시작일은 현재일 보다 커야합니다.
		if (compare <= 0) {
			// 사전 등록 불가
			throw new MissionRegisterException(ErrorCode.INVALID_PERIOD);
		}
	}
}
