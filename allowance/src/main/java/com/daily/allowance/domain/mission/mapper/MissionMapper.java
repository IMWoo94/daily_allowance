package com.daily.allowance.domain.mission.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.mission.dao.MissionDao;

@MyBatisMapper
public interface MissionMapper {

	// void findAll();
	//
	List<MissionDao> searchAvailableMission(boolean active);
	//
	// void insertMission();
	//
	// void updateMission();
}
