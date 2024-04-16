package com.daily.allowance.domain.mission.dto;

import java.time.LocalDate;

import com.daily.allowance.common.annotation.DailyDateFormat;
import com.daily.allowance.common.domain.Member;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionChallengeRequestDto {

	private Member member;
	@NotNull
	private Long missionId;
	@NotNull
	@DailyDateFormat
	private LocalDate applyDate;
}
