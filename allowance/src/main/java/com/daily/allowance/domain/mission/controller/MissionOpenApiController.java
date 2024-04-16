package com.daily.allowance.domain.mission.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

@RestController
@RequestMapping("/open-api/mission")
public class MissionOpenApiController {

	private final MissionBusiness missionBusiness;

	public MissionOpenApiController(MissionBusiness missionBusiness) {
		this.missionBusiness = missionBusiness;
	}

	/**
	 * [Mission] - 미션 목록 조회
	 */
	@GetMapping
	public Api searchAvailableMission() {
		List<MissionResponseDto> response = missionBusiness.searchAvailableMission();
		return Api.OK(response);
	}

	/**
	 * [Mission] - 미션 도전
	 */
	public Api missionChallenge() {

		return Api.OK();
	}
}
