package com.daily.allowance.domain.mission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.mission.dto.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;
import com.daily.allowance.domain.mission.mapper.MissionMapper;

@Service
public class MissionService {

	private final MissionMapper missionMapper;

	public MissionService(MissionMapper missionMapper) {
		this.missionMapper = missionMapper;
	}

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	public List<MissionResponseDto> searchAvailableMission() {
		return missionMapper.searchAvailableMission();
	}

	public void registerMission(MissionRegisterRequestDto missionRegisterRequestDto) {
		missionMapper.registerMission(missionRegisterRequestDto);
	}
}
