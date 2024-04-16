package com.daily.allowance.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MissionModifiedActiveRequestDto {
	@NotNull
	private Long missionId;
	@NotNull
	private boolean active;
}
