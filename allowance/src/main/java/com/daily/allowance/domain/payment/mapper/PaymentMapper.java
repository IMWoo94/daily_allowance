package com.daily.allowance.domain.payment.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.payment.dto.PaymentHistoryRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentHistoryResponseDto;

@MyBatisMapper
public interface PaymentMapper {

	List<PaymentHistoryResponseDto> searchPaymentHistory(PaymentHistoryRequestDto paymentHistoryRequestDto);
}
