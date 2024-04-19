package com.daily.allowance.domain.payment.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.annotation.User;
import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.code.SuccessCode;
import com.daily.allowance.common.model.InfoContext;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.payment.business.PaymentBusiness;
import com.daily.allowance.domain.payment.dto.request.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.dto.response.PaymentBenefitResponseDto;
import com.daily.allowance.domain.payment.dto.response.PaymentResponseDto;
import com.daily.allowance.domain.payment.exception.PaymentException;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/open-api/payment")
@RequiredArgsConstructor
@Slf4j
public class PaymentOpenApiController {

	private final PaymentBusiness paymentBusiness;

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	@GetMapping("/history/{year}/{month}")
	public Api searchPayment(
		@User @Parameter(hidden = true) Member member,
		@PathVariable int year,
		@PathVariable int month
	) {
		PaymentSearchRequestDto request = new PaymentSearchRequestDto(year, month, member);
		PaymentBenefitResponseDto response = paymentBusiness.searchPaymentBenefitMonthly(request);
		return Api.OK(response, SuccessCode.PAYMENT_SEARCH_COMPLETE);
	}

	/**
	 * [ payment ] - 데일리 용돈 받기
	 */
	@PostMapping("/dailyAllowance")
	public Api dailyAllowancePayment(
		@User @Parameter(hidden = true) Member member,
		@RequestBody PaymentDailyRequestDto paymentDailyRequestDto
	) {
		PaymentResponseDto response = paymentBusiness.dailyAllowancePayment(member, paymentDailyRequestDto);
		return Api.OK(response, SuccessCode.PAYMENT_DAILY_ALLOWANCE_COMPLETE);
	}

	/**
	 * [ payment ] - 미션 도전
	 */
	@PostMapping("/mission")
	public Api missionPayment(
		@User @Parameter(hidden = true) Member member,
		@RequestBody @Valid PaymentMissionRequestDto paymentMissionRequestDto
	) {
		PaymentResponseDto response = paymentBusiness.missionPayment(member, paymentMissionRequestDto);
		return Api.OK(response, SuccessCode.PAYMENT_MISSION_COMPLETE);
	}

	/**
	 * 혜택 지급 관련 예외
	 */
	@ExceptionHandler(PaymentException.class)
	public Api paymentExceptionHandler(PaymentException e) {
		InfoContext.set("exception", e.getMessage());
		return Api.ERROR(e.getErrorCodeIfs(), e.getErrorDescription());
	}

	/**
	 * 정의하지 않은 예외
	 */
	@ExceptionHandler(RuntimeException.class)
	public Api unknownExceptionHandler(RuntimeException e) {
		InfoContext.set("exception", e.getMessage());
		return Api.ERROR(ErrorCode.UNKNOWN_ERROR);
	}
}
