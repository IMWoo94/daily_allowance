package com.daily.allowance.domain.mission.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.mission.dao.MissionDao;
import com.daily.allowance.domain.mission.mapper.MissionMapper;

@Service
public class MissionService {

	private final MissionMapper missionMapper;

	public MissionService(MissionMapper missionMapper) {
		this.missionMapper = missionMapper;
	}

	public void findAll() {

	}

	public List<MissionDao> searchAvailableMission() {
		return missionMapper.searchAvailableMission(true);
	}
}
