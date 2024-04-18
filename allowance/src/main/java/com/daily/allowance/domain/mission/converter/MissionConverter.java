package com.daily.allowance.domain.mission.converter;

import java.util.Optional;

import com.daily.allowance.common.annotation.Converter;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.domain.mission.dto.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;
import com.daily.allowance.domain.mission.exception.MissionException;

@Converter
public class MissionConverter {

	public MissionResponseDto toResponse(MissionRegisterRequestDto request) {
		return Optional.ofNullable(request)
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
			.orElseThrow(() -> new MissionException(ErrorCode.NULL_POINT));
	}
}
