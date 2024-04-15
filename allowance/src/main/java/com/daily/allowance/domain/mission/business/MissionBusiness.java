package com.daily.allowance.domain.mission.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.domain.mission.converter.MissionConverter;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;
import com.daily.allowance.domain.mission.service.MissionService;

@Business
public class MissionBusiness {

	private final MissionService missionService;
	private final MissionConverter missionConverter;

	public MissionBusiness(MissionService missionService, MissionConverter missionConverter) {
		this.missionService = missionService;
		this.missionConverter = missionConverter;
	}

	public List<MissionResponseDto> searchMission() {
		var missions = missionService.findAllByActive();

		return missions.stream()
			.map(missionConverter::toResponse)
			.toList();
	}
}
