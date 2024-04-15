package com.daily.allowance.domain.mission.dao;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MissionDao {

	private Long missionId;
	private String missionName;
	private Integer missionAmount;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;
}
