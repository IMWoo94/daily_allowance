package com.daily.allowance.domain.payment.dto.response;

import java.util.List;

import com.daily.allowance.domain.payment.vo.PaymentSearchResponseVo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentBenefitResponseDto {

	private Integer totalAmount;
	private List<PaymentSearchResponseVo> results;

}
