package com.daily.allowance.domain.payment.dto;

import java.time.LocalDate;
import java.time.YearMonth;

import com.daily.allowance.common.annotation.DailyDateFormat;
import com.daily.allowance.common.model.Member;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PaymentSearchRequestDto {

	@NotNull
	@DailyDateFormat
	private LocalDate startDate;
	@NotNull
	@DailyDateFormat
	private LocalDate endDate;
	private Member member;

	public PaymentSearchRequestDto(int year, int month, Member member) {
		// 년월
		YearMonth yearMonth = YearMonth.of(year, month);

		this.startDate = yearMonth.atDay(1);    // 해당 월의 첫날.
		this.endDate = yearMonth.atEndOfMonth(); // 해당 월의 마지막날.
		this.member = member;
	}
}
