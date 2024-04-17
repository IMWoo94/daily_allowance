package com.daily.allowance.domain.mission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.mission.dto.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;
import com.daily.allowance.domain.mission.dto.MissionSearchRequestDto;

@MyBatisMapper
public interface MissionMapper {

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	List<MissionResponseDto> searchMission(MissionSearchRequestDto missionSearchRequestDto);

	/**
	 * [Mission] - 가능 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	@Select("select * from mission where active = true and CURRENT_DATE between start_date and end_date")
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
