package com.daily.allowance.domain.mission.exception;

import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.exception.ExceptionIfs;

import lombok.Getter;

@Getter
public class MissionException extends RuntimeException implements ExceptionIfs {

	private final CodeIfs errorCodeIfs;
	private final String errorDescription;

	public MissionException(CodeIfs errorCodeIfs) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}
}
