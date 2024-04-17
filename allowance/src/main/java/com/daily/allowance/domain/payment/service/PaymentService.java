package com.daily.allowance.domain.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.payment.dto.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.mapper.PaymentMapper;

@Service
public class PaymentService {

	private final PaymentMapper paymentMapper;

	public PaymentService(PaymentMapper paymentMapper) {
		this.paymentMapper = paymentMapper;
	}

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	public List<PaymentResponseDto> searchPayment(PaymentSearchRequestDto request) {
		return paymentMapper.searchPayment(request);
	}

	public List<PaymentResponseDto> searchDuplicatePayment(PaymentRequestDto request) {
		return paymentMapper.searchDuplicatePayment(request);
	}
}
