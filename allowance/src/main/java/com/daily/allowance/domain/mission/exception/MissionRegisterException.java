package com.daily.allowance.domain.mission.exception;

import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.exception.ExceptionIfs;

import lombok.Getter;

@Getter
public class MissionRegisterException extends RuntimeException implements ExceptionIfs {

	private final CodeIfs errorCodeIfs;
	private final String errorDescription;

	public MissionRegisterException(CodeIfs errorCodeIfs) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}

	public MissionRegisterException(CodeIfs errorCodeIfs, String errorDescription) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorDescription;
	}

	public MissionRegisterException(CodeIfs errorCodeIfs, Throwable throwable) {
		super(throwable);
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}

	public MissionRegisterException(CodeIfs errorCodeIfs, Throwable throwable, String errorDescription) {
		super(throwable);
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorDescription;
	}
}
