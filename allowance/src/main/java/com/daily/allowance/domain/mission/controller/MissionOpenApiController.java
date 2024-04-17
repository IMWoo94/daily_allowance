package com.daily.allowance.domain.mission.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.annotation.User;
import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.domain.Member;
import com.daily.allowance.domain.mission.business.MissionBusiness;
import com.daily.allowance.domain.mission.dto.MissionChallengeRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/open-api/mission")
@RequiredArgsConstructor
public class MissionOpenApiController {

	private final MissionBusiness missionBusiness;

	/**
	 * [Mission] - 가능 미션 목록 조회
	 */
	@GetMapping
	public Api searchAvailableMission() {
		List<MissionResponseDto> response = missionBusiness.searchAvailableMission();
		return Api.OK(response);
	}

	/**
	 * [Mission] - 미션 도전
	 */
	@PostMapping("/challenge/{missionId}")
	public Api missionChallenge(
		@User @Parameter(hidden = true) Member member,
		@PathVariable Long missionId
	) {
		missionBusiness.missionChallenge(MissionChallengeRequestDto.builder()
			.missionId(missionId)
			.member(member)
			.applyDate(LocalDate.now())
			.build());
		return Api.OK();
	}
}
