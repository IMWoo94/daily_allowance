package com.daily.allowance.domain.payment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.payment.dto.request.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.request.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.dto.response.PaymentResponseDto;
import com.daily.allowance.domain.payment.mapper.PaymentMapper;
import com.daily.allowance.domain.payment.vo.PaymentSearchResponseVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final PaymentMapper paymentMapper;

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	public List<PaymentSearchResponseVo> searchPaymentBenefitMonthly(PaymentSearchRequestDto request) {
		return paymentMapper.searchPaymentBenefitMonthly(request);
	}

	/**
	 * [ payment ] - 지급 등록
	 */
	public int registerPayment(PaymentRequestDto request) {
		return paymentMapper.registerPayment(request);
	}

	/**
	 * [ payment ] - 지급 히스토리 등록
	 */
	public int registerPaymentHistory(PaymentHistoryRequestDto request) {
		return paymentMapper.registerPaymentHistory(request);
	}

	/**
	 * [ payment ] - 지급 완료 처리
	 */
	public int modifiedPayment(PaymentRequestDto request) {
		return paymentMapper.modifiedPayment(request);
	}

	/**
	 * [ payment ] - 중복 참여 검증
	 */
	public PaymentResponseDto searchDuplicatePayment(PaymentRequestDto request) {
		return paymentMapper.searchDuplicatePayment(request);
	}

}
