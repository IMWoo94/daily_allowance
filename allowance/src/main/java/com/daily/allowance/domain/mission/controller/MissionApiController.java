package com.daily.allowance.domain.mission.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.SuccessCode;
import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.dto.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mission")
public class MissionApiController {

	private final MissionBusiness missionBusiness;

	public MissionApiController(MissionBusiness missionBusiness) {
		this.missionBusiness = missionBusiness;
	}

	/**
	 * [Mission] - 미션 등록
	 */
	@PostMapping("/register")
	public Api missionRegister(
		@RequestBody @Valid MissionRegisterRequestDto missionRegisterRequestDto
	) {
		MissionResponseDto response = missionBusiness.missionRegister(missionRegisterRequestDto);
		return Api.OK(response, missionRegisterRequestDto, SuccessCode.MISSION_REGISTER);
	}

	/**
	 * [Mission] - 미션 활성화, 비활성화
	 */
	@PostMapping("/modified/active")
	public void missionModifiedActive(
		@RequestBody @Valid MissionModifiedActiveRequestDto missionModifiedActiveRequestDto
	) {
		missionBusiness.missionModifiedActive();
	}

}
