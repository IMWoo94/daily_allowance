package com.daily.allowance.domain.mission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.mission.dto.MissionModifiedActiveRequestDto;
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

	/**
	 * [ Mission ] - 미션 등록
	 * @param missionRegisterRequestDto
	 */
	public int registerMission(MissionRegisterRequestDto missionRegisterRequestDto) {
		return missionMapper.registerMission(missionRegisterRequestDto);
	}

	/**
	 * [ Mission ] - 미션 수정 ( 활성화, 비활성화 )
	 * @param missionModifiedActiveRequestDto
	 */
	public int modifiedMissionActive(MissionModifiedActiveRequestDto missionModifiedActiveRequestDto) {
		return missionMapper.modifiedMissionActive(missionModifiedActiveRequestDto);
	}
}
