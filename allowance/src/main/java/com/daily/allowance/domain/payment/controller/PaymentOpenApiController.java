package com.daily.allowance.domain.payment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.annotation.User;
import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.payment.business.PaymentBusiness;
import com.daily.allowance.domain.payment.dto.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentSearchRequestDto;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/open-api/payment")
public class PaymentOpenApiController {

	private final PaymentBusiness paymentBusiness;

	public PaymentOpenApiController(PaymentBusiness paymentBusiness) {
		this.paymentBusiness = paymentBusiness;
	}

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
		List<PaymentResponseDto> response = paymentBusiness.searchPayment(request);
		return Api.OK(response);
	}

	/**
	 * [ payment ] - 데일리 용돈 받기
	 */
	@PostMapping("/dailyAllowance")
	public Api dailyAllowancePayment(
		@User @Parameter(hidden = true) Member member,
		@RequestBody PaymentDailyRequestDto paymentDailyRequestDto
	) {
		paymentBusiness.dailyAllowancePayment(member, paymentDailyRequestDto);
		return Api.OK();
	}

	/**
	 * [ payment ] - 미션 도전
	 */
	@PostMapping("/mission")
	public Api missionPayment(
		@User @Parameter(hidden = true) Member member,
		@RequestBody @Valid PaymentMissionRequestDto paymentMissionRequestDto
	) {
		paymentBusiness.missionPayment(member, paymentMissionRequestDto);
		return Api.OK();
	}
}
