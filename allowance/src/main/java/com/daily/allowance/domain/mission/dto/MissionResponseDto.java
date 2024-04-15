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
public class MissionResponseDto {
	private Long missionId;
	private String missionName;
	private Integer missionAmount;
	private LocalDate startDate;
	private LocalDate endDate;
	private boolean active;
}
