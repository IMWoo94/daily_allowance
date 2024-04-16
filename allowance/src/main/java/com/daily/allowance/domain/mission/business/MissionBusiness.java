package com.daily.allowance.domain.mission.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.domain.mission.converter.MissionConverter;
import com.daily.allowance.domain.mission.dto.MissionChallengeRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;
import com.daily.allowance.domain.mission.service.MissionService;

@Business
public class MissionBusiness {

	private final MissionService missionService;
	private final MissionConverter missionConverter;

	public MissionBusiness(MissionService missionService, MissionConverter missionConverter) {
		this.missionService = missionService;
		this.missionConverter = missionConverter;
	}

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	public List<MissionResponseDto> searchAvailableMission() {
		return missionService.searchAvailableMission();
	}

	public void missionChallenge(MissionChallengeRequestDto missionChallengeRequestDto) {
		// 미션 도전

		// TODO
		/**
		 * 미션 검증 필요
		 * 1. 미션 기간 및 운영 상태 확인
		 */

		// TODO
		/**
		 * 지급 검증 필요
		 * 1. 금액 ( 미션 금액과 지급 금액 확인 )
		 * 2. 미션 중복 지급 여부 확인
		 *
		 * 지급 처리 - Payment 영역
		 * 1. 지급 내역 등록 및 히스토리 등록
		 * 2. 혜택 지급 - 입금 모듈 호출 ( 주석으로 표시 )
		 * 3. 지급 완료 처리
		 */

	}
}
