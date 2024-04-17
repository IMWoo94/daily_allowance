package com.daily.allowance.domain.payment.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.payment.dto.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentSearchRequestDto;

@MyBatisMapper
public interface PaymentMapper {

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	List<PaymentResponseDto> searchPayment(PaymentSearchRequestDto request);

	/**
	 * [ payment ] - 기지급 목록 조회
	 * @return List<PaymentResponseDto>
	 */
	List<PaymentResponseDto> searchDuplicatePayment(PaymentRequestDto request);
}
