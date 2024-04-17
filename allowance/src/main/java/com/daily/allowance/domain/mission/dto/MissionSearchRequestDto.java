package com.daily.allowance.domain.mission.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionSearchRequestDto {

	private LocalDate currentDate;
	private String missionName;
	private Integer missionAmount;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean active;
}
