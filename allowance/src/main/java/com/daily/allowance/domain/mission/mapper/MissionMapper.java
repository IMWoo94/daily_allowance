package com.daily.allowance.domain.mission.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;

@MyBatisMapper
public interface MissionMapper {

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	List<MissionResponseDto> searchAvailableMission();
}
