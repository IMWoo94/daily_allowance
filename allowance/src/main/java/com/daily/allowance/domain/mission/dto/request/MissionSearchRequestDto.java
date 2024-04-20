package com.daily.allowance.domain.mission.dto.request;

import java.time.LocalDate;

import com.daily.allowance.common.annotation.DailyDateFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionSearchRequestDto {
	
	private String missionName;
	private Integer missionAmount;
	@DailyDateFormat
	private LocalDate startDate;
	@DailyDateFormat
	private LocalDate endDate;
	private Boolean active;
}
