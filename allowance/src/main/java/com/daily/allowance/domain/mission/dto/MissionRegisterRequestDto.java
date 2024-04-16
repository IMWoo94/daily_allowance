package com.daily.allowance.domain.mission.dto;

import java.time.LocalDate;

import com.daily.allowance.common.annotation.DailyDateFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionRegisterRequestDto {

	@NotNull
	private String missionName;
	@NotNull
	private Integer missionAmount;
	@NotNull
	@DailyDateFormat
	private LocalDate startDate;
	@NotNull
	@DailyDateFormat
	private LocalDate endDate;
	@NotNull
	private boolean active;
}
