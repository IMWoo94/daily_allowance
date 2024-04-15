package com.daily.allowance.domain.mission.converter;

import java.util.Optional;

import com.daily.allowance.common.annotation.Converter;
import com.daily.allowance.domain.mission.dao.MissionDao;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

@Converter
public class MissionConverter {

	public MissionResponseDto toResponse(MissionDao response) {
		return Optional.ofNullable(response)
			.map(it -> {
				return MissionResponseDto.builder()
					.missionId(it.getMissionId())
					.missionName(it.getMissionName())
					.missionAmount(it.getMissionAmount())
					.startDate(it.getStartDate())
					.endDate(it.getEndDate())
					.active(it.isActive())
					.build();
			})
			.orElseThrow(() -> new RuntimeException("MissionConverter response null point"));
	}
}
