package com.daily.allowance.domain.payment.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.domain.payment.business.PaymentBusiness;
import com.daily.allowance.domain.payment.dto.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentHistoryResponseDto;

@RestController
@RequestMapping("/open-api/payment")
public class PaymentOpenApiController {

	private final PaymentBusiness paymentBusiness;

	public PaymentOpenApiController(PaymentBusiness paymentBusiness) {
		this.paymentBusiness = paymentBusiness;
	}

	@GetMapping("/history/{year}/{month}")
	public List<PaymentHistoryResponseDto> searchPaymentHistory(
		@PathVariable int year,
		@PathVariable int month
	) {
		PaymentHistoryRequestDto paymentHistoryRequestDto = new PaymentHistoryRequestDto(year, month);
		return paymentBusiness.searchPaymentHistory(paymentHistoryRequestDto);
	}
}
