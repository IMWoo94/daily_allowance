package com.daily.allowance.domain.mission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.mission.dto.request.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.request.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.response.MissionResponseDto;
import com.daily.allowance.domain.mission.dto.request.MissionSearchRequestDto;
import com.daily.allowance.domain.mission.mapper.MissionMapper;
import com.daily.allowance.domain.mission.vo.MissionResponseVo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MissionService {

	private final MissionMapper missionMapper;

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List
	 */
	public List<MissionResponseDto> searchMission(MissionSearchRequestDto request) {
		return missionMapper.searchMission(request);
	}

	/**
	 * [Mission] - 가능 미션 목록 조회
	 * @return List
	 */
	public List<MissionResponseDto> searchAvailableMission() {
		return missionMapper.searchAvailableMission();
	}

	/**
	 * [ Mission ] - 미션 등록
	 */
	public int registerMission(MissionRegisterRequestDto request) {
		return missionMapper.registerMission(request);
	}

	/**
	 * [ Mission ] - 미션 수정 ( 활성화, 비활성화 )
	 */
	public int modifiedMissionActive(MissionModifiedActiveRequestDto request) {
		return missionMapper.modifiedMissionActive(request);
	}

	/**
	 * [ Mission ] - 미션 상세 조회
	 */
	public MissionResponseVo searchMissionDetailById(Long missionId) {
		return missionMapper.searchMissionDetailById(missionId);
	}
}
