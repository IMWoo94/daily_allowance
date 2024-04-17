package com.daily.allowance.domain.mission.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.mission.dto.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

@MyBatisMapper
public interface MissionMapper {

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	List<MissionResponseDto> searchAvailableMission();

	/**
	 * [ Mission ] - 미션 등록
	 * @param missionRegisterRequestDto
	 */
	int registerMission(MissionRegisterRequestDto missionRegisterRequestDto);

	/**
	 * [ Mission ] - 미션 수정 ( 활성화, 비활성화 )
	 * @param missionModifiedActiveRequestDto
	 */
	int modifiedMissionActive(MissionModifiedActiveRequestDto missionModifiedActiveRequestDto);
}
