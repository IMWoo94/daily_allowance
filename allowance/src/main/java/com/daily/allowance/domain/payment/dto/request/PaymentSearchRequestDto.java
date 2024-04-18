package com.daily.allowance.domain.payment.dto.request;

import java.time.LocalDate;
import java.time.YearMonth;

import com.daily.allowance.common.model.Member;

import lombok.Getter;

@Getter
public class PaymentSearchRequestDto {

	private LocalDate startDate;
	private LocalDate endDate;
	private Long memberId;

	public PaymentSearchRequestDto(int year, int month, Member member) {
		// 년월
		YearMonth yearMonth = YearMonth.of(year, month);

		this.startDate = yearMonth.atDay(1);    // 해당 월의 첫날.
		this.endDate = yearMonth.atEndOfMonth(); // 해당 월의 마지막날.
		this.memberId = member.getMemberId();
	}
}
