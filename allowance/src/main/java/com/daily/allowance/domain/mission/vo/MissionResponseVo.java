package com.daily.allowance.domain.mission.vo;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class MissionResponseVo {
	private Long missionId;
	private String missionName;
	private Integer missionAmount;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;

	public boolean operatedPeriodCheck(LocalDate currentDate) {
		LocalDate sDate = this.startDate.minusDays(1);
		LocalDate eDate = this.endDate.plusDays(1);
		return currentDate.isAfter(sDate) && currentDate.isBefore(eDate) && isActive();
	}

	public boolean amountCheck(int amount) {
		return this.missionAmount == amount;
	}
}
