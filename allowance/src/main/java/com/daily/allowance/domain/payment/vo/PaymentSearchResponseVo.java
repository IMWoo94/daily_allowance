package com.daily.allowance.domain.payment.vo;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class PaymentSearchResponseVo {

	private LocalDate paymentDate;
	private String paymentName;
	private Integer paymentAmount;

}
