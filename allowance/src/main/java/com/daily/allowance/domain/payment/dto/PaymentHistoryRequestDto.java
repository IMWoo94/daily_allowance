package com.daily.allowance.domain.payment.dto;

import java.time.LocalDate;
import java.time.YearMonth;

import lombok.Getter;

@Getter
public class PaymentHistoryRequestDto {

	private LocalDate startDate;
	private LocalDate endDate;

	public PaymentHistoryRequestDto(int year, int month) {
		// 년월
		YearMonth yearMonth = YearMonth.of(year, month);
		if (yearMonth.isValidDay(1)) {
			throw new IllegalArgumentException("형식이 잘못 되었습니다.");
		}
		this.startDate = yearMonth.atDay(1);    // 해당 월의 첫날.
		this.endDate = yearMonth.atEndOfMonth(); // 해당 월의 마지막날.
	}
}
