package com.daily.allowance.common.controlleradvice;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.exception.DateTypeInvalidException;
import com.daily.allowance.domain.mission.exception.MissionRegisterException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

	@ExceptionHandler(DateTypeInvalidException.class)
	public Api dateTypeInvalidExceptionHandler(DateTypeInvalidException e) {
		return Api.ERROR(e.getErrorCodeIfs(), e.getErrorDescription());
	}

	@ExceptionHandler(HttpMessageConversionException.class)
	public Api httpMessageConversionExceptionHandler(HttpMessageConversionException e) {
		return Api.ERROR(ErrorCode.BAD_REQUEST, e);
	}

	@ExceptionHandler(MissionRegisterException.class)
	public Api missionRegisterExceptionHandler(MissionRegisterException e) {
		return Api.ERROR(e.getErrorCodeIfs());
	}

	@ExceptionHandler(RuntimeException.class)
	public Api unknownExceptionHandler(RuntimeException e) {
		log.info("Exception {}", e.getClass().getName());
		e.fillInStackTrace();
		return Api.ERROR(ErrorCode.UNKNOWN_ERROR);
	}
}
