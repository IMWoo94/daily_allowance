package com.daily.allowance.domain.mission.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.SuccessCode;
import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.dto.request.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.request.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.response.MissionResponseDto;
import com.daily.allowance.domain.mission.dto.request.MissionSearchRequestDto;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mission")
@RequiredArgsConstructor
public class MissionApiController {

	private final MissionBusiness missionBusiness;

	/**
	 * [Mission] - 미션 조회
	 */
	@PostMapping
	public Api searchMission(
		@RequestBody @Valid MissionSearchRequestDto request
	) {
		List<MissionResponseDto> response = missionBusiness.searchMission(request);
		return Api.OK(response, request, SuccessCode.OK);
	}

	/**
	 * [Mission] - 미션 등록
	 */
	@PostMapping("/register")
	public Api registerMission(
		@RequestBody @Valid MissionRegisterRequestDto request
	) {
		MissionResponseDto response = missionBusiness.registerMission(request);
		return Api.OK(response, request, SuccessCode.MISSION_REGISTER_COMPLETE);
	}

	/**
	 * [Mission] - 미션 활성화, 비활성화
	 */
	@PostMapping("/modified/active")
	public Api modifiedMissionActive(
		@RequestBody @Valid MissionModifiedActiveRequestDto request
	) {
		missionBusiness.modifiedMissionActive(request);
		return Api.OK(null, request, SuccessCode.MISSION_MODIFIED_COMPLETE);
	}

}
