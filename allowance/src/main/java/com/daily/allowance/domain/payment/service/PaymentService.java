package com.daily.allowance.domain.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.payment.dto.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentHistoryResponseDto;
import com.daily.allowance.domain.payment.mapper.PaymentMapper;

@Service
public class PaymentService {

	private final PaymentMapper paymentMapper;

	public PaymentService(PaymentMapper paymentMapper) {
		this.paymentMapper = paymentMapper;
	}

	public List<PaymentHistoryResponseDto> searchPaymentHistory(PaymentHistoryRequestDto paymentHistoryRequestDto) {
		return paymentMapper.searchPaymentHistory(paymentHistoryRequestDto);
	}
}
