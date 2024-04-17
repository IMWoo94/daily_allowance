package com.daily.allowance.domain.payment.exception;

import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.exception.ExceptionIfs;

import lombok.Getter;

@Getter
public class PaymentException extends RuntimeException implements ExceptionIfs {

	private final CodeIfs errorCodeIfs;
	private final String errorDescription;

	public PaymentException(CodeIfs errorCodeIfs) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}

	public PaymentException(CodeIfs errorCodeIfs, String errorDescription) {
		super(errorCodeIfs.getMessage());
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorDescription;
	}

	public PaymentException(CodeIfs errorCodeIfs, Throwable throwable) {
		super(throwable);
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorCodeIfs.getDescription();
	}

	public PaymentException(CodeIfs errorCodeIfs, Throwable throwable, String errorDescription) {
		super(throwable);
		this.errorCodeIfs = errorCodeIfs;
		this.errorDescription = errorDescription;
	}

}
