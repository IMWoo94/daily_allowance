package com.daily.allowance.common.exception;

import com.daily.allowance.common.code.CodeIfs;

import lombok.Getter;

@Getter
public class DateTypeInvalidException extends RuntimeException implements ExceptionIfs {

	private final CodeIfs errorCodeIfs;
	private final String errorDescription;

	public DateTypeInvalidException(CodeIfs errorCodeIfs) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}

	public DateTypeInvalidException(CodeIfs errorCodeIfs, String errorDescription) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorDescription;
	}

	public DateTypeInvalidException(CodeIfs errorCodeIfs, Throwable throwable) {
		super(throwable);
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}

	public DateTypeInvalidException(CodeIfs errorCodeIfs, Throwable throwable, String errorDescription) {
		super(throwable);
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorDescription;
	}

}
