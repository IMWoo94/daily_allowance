package com.daily.allowance.domain.mission.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.mission.dto.request.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.request.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.response.MissionResponseDto;
import com.daily.allowance.domain.mission.dto.request.MissionSearchRequestDto;
import com.daily.allowance.domain.mission.vo.MissionResponseVo;

@MyBatisMapper
public interface MissionMapper {

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List
	 */
	List<MissionResponseDto> searchMission(MissionSearchRequestDto request);

	/**
	 * [Mission] - 가능 미션 목록 조회
	 * @return List
	 */
	@Select("select * from mission where active = true and CURRENT_DATE between start_date and end_date")
	List<MissionResponseDto> searchAvailableMission();

	/**
	 * [ Mission ] - 미션 등록
	 */
	int registerMission(MissionRegisterRequestDto request);

	/**
	 * [ Mission ] - 미션 수정 ( 활성화, 비활성화 )
	 */
	int modifiedMissionActive(MissionModifiedActiveRequestDto request);

	/**
	 * [ Mission ] - 미션 상세 조회
	 */
	@Select("select * from mission where mission_id = #{missionId}")
	MissionResponseVo searchMissionDetailById(Long missionId);
}
