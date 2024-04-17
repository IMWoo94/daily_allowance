package com.daily.allowance.common.controlleradvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

	@ExceptionHandler(RuntimeException.class)
	public Api unknownExceptionHandler(RuntimeException e) {
		log.info("Exception {}", e.getClass().getName());
		return Api.ERROR(ErrorCode.UNKNOWN_ERROR);
	}
}
