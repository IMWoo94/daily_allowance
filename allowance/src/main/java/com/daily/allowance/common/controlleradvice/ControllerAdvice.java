package com.daily.allowance.common.controlleradvice;

import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.exception.DateTypeInvalidException;
import com.daily.allowance.domain.mission.exception.MissionException;
import com.daily.allowance.domain.payment.exception.PaymentException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

	/**
	 * Parameter LocalDate 포맷 불일치 예외
	 * @DailyDateFormat
	 */
	@ExceptionHandler(DateTypeInvalidException.class)
	public Api dateTypeInvalidExceptionHandler(DateTypeInvalidException e) {
		return Api.ERROR(e.getErrorCodeIfs(), e.getErrorDescription());
	}

	/**
	 * java validation 예외
	 */
	@ExceptionHandler(HttpMessageConversionException.class)
	public Api httpMessageConversionExceptionHandler(HttpMessageConversionException e) {
		return Api.ERROR(ErrorCode.BAD_REQUEST, e);
	}

	/**
	 * 미션 관련 예외
	 */
	@ExceptionHandler(MissionException.class)
	public Api missionExceptionHandler(MissionException e) {
		return Api.ERROR(e.getErrorCodeIfs());
	}

	/**
	 * 혜택 지급 관련 예외
	 */
	@ExceptionHandler(PaymentException.class)
	public Api paymentExceptionHandler(PaymentException e) {
		return Api.ERROR(e.getErrorCodeIfs());
	}

	/**
	 * 정의하지 않은 예외
	 */
	@ExceptionHandler(RuntimeException.class)
	public Api unknownExceptionHandler(RuntimeException e) {
		return Api.ERROR(ErrorCode.UNKNOWN_ERROR, e.fillInStackTrace());
	}
}
