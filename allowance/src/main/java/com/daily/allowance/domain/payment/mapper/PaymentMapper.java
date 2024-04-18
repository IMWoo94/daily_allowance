package com.daily.allowance.domain.payment.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.payment.dto.PaymentHistoryDto;
import com.daily.allowance.domain.payment.dto.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.vo.PaymentSearchResponseVo;

@MyBatisMapper
public interface PaymentMapper {

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	List<PaymentSearchResponseVo> searchPaymentBenefitMonthly(PaymentSearchRequestDto request);

	/**
	 * [ payment ] - 지급 등록
	 */
	int registerPayment(PaymentRequestDto request);

	/**
	 * [ payment ] - 지급 히스토리 등록
	 */
	int registerPaymentHistory(PaymentHistoryDto request);

	/**
	 * [ payment ] - 지급 완료 처리
	 */
	int modifiedPayment(PaymentRequestDto request);

	/**
	 * [ payment ] - 중복 참여 검증
	 */
	PaymentResponseDto searchDuplicatePayment(PaymentRequestDto request);

}
