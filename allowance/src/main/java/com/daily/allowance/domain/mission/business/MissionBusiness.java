package com.daily.allowance.domain.mission.business;

import java.time.LocalDate;
import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.common.validator.DateValidator;
import com.daily.allowance.domain.mission.converter.MissionConverter;
import com.daily.allowance.domain.mission.dto.MissionChallengeRequestDto;
import com.daily.allowance.domain.mission.dto.MissionModifiedActiveRequestDto;
import com.daily.allowance.domain.mission.dto.MissionRegisterRequestDto;
import com.daily.allowance.domain.mission.dto.MissionResponseDto;
import com.daily.allowance.domain.mission.dto.MissionSearchRequestDto;
import com.daily.allowance.domain.mission.exception.MissionRegisterException;
import com.daily.allowance.domain.mission.service.MissionService;
import com.daily.allowance.domain.mission.validator.MissionValidator;

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class MissionBusiness {

	private final MissionService missionService;
	private final MissionConverter missionConverter;

	/**
	 * [Mission] - 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	public List<MissionResponseDto> searchMission(MissionSearchRequestDto missionSearchRequestDto) {
		return missionService.searchMission(missionSearchRequestDto);
	}

	/**
	 * [Mission] - 가능 미션 목록 조회
	 * @return List<MissionResponseDto>
	 */
	public List<MissionResponseDto> searchAvailableMission() {
		return missionService.searchAvailableMission();
	}

	/**
	 * [ Mission ] - 미션 등록
	 * 1. 미션 운영 기간 검증
	 * 2. 신규 미션 등록
	 */
	public MissionResponseDto registerMission(MissionRegisterRequestDto missionRegisterRequestDto) {
		// 1. 미션 운영 기간 검증
		LocalDate sDate = missionRegisterRequestDto.getStartDate();
		LocalDate eDate = missionRegisterRequestDto.getEndDate();
		missionDateValidate(sDate, eDate);

		// 2. 신규 미션 등록
		int result = missionService.registerMission(missionRegisterRequestDto);

		if (result == 0) {
			throw new MissionRegisterException(ErrorCode.MISSION_REGISTER_FAIL);
		}

		// 응답 결과 리턴 ( 미선 ID 값 추가 )
		return missionConverter.toResponse(missionRegisterRequestDto);

	}

	/**
	 * [ Mission ] - 미션 운영 기간 검증
	 * 1. 날짜 데이터 타입 유효성 검증 ( 시작일, 종료일 )
	 * 2. 시작일, 종료일 유효성 검증 ( 종료일이 시작일 보다 커야한다. )
	 * 3. 운영 기간 사전 등록 검증 ( 시작일이 현재일 보다 커야한다. )
	 */
	private void missionDateValidate(LocalDate sDate, LocalDate eDate) {
		// 1. 날짜 데이터 타입 유효성 검증 ( 시작일, 종료일 )
		DateValidator.dateFormatValidateWithThrow(sDate, eDate);

		// 2. 시작일, 종료일 유효성 검증 ( 종료일이 시작일 보다 커야한다. )
		DateValidator.compareDateWithThrow(sDate, eDate);

		// 3. 운영 기간 사전 등록 검증 ( 시작일이 현재일 보다 커야한다. )
		MissionValidator.periodValidateWithThrow(sDate);
	}

	/**
	 * [ Mission ] - 미션 수정 ( 활성화, 비활성화 )
	 * 1. 미션 활성화 수정
	 */
	public void modifiedMissionActive(MissionModifiedActiveRequestDto missionModifiedActiveRequestDto) {
		// 1. 미션 활성화 수정
		int result = missionService.modifiedMissionActive(missionModifiedActiveRequestDto);

		if (result == 0) {
			throw new MissionRegisterException(ErrorCode.MISSION_MODIFIED_FAIL);
		}
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
