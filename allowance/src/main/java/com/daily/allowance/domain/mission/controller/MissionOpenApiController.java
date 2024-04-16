package com.daily.allowance.domain.mission.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

@RestController
@RequestMapping("/open-api/mission")
public class MissionOpenApiController {

	private final MissionBusiness missionBusiness;

	public MissionOpenApiController(MissionBusiness missionBusiness) {
		this.missionBusiness = missionBusiness;
	}

	@GetMapping
	public List<MissionResponseDto> searchAvailableMission() {
		return missionBusiness.searchAvailableMission();
	}
}
