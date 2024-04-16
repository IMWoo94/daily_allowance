package com.daily.allowance.domain.payment.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.domain.payment.dto.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentHistoryResponseDto;
import com.daily.allowance.domain.payment.service.PaymentService;

@Business
public class PaymentBusiness {

	private final PaymentService paymentService;

	public PaymentBusiness(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public List<PaymentHistoryResponseDto> searchPaymentHistory(PaymentHistoryRequestDto paymentHistoryRequestDto) {
		return paymentService.searchPaymentHistory(paymentHistoryRequestDto);
	}
}
