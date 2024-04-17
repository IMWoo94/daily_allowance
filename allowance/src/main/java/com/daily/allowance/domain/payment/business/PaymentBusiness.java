package com.daily.allowance.domain.payment.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.domain.payment.converter.PaymentConverter;
import com.daily.allowance.domain.payment.dto.PaymentDailyRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentMissionRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentRequestDto;
import com.daily.allowance.domain.payment.dto.PaymentResponseDto;
import com.daily.allowance.domain.payment.dto.PaymentSearchRequestDto;
import com.daily.allowance.domain.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class PaymentBusiness {

	private final PaymentService paymentService;
	private final PaymentConverter paymentConverter;

	/**
	 * [ payment ] - 지급 내역 월별 조회
	 */
	public List<PaymentResponseDto> searchPayment(PaymentSearchRequestDto request) {
		return paymentService.searchPayment(request);
	}

	/**
	 * [ payment ] - 데일리 용돈 받기
	 * 데일리 용돈 검증 필요
	 * 1. 데일리 용돈 금액 확인
	 * 2. 금일 참여 확인
	 *
	 * 지급 처리
	 * 1. 지급 내역 등록 및 히스토리 등록
	 * 2. 혜택 지급 - 입금 모듈 호출 ( 주석으로 표시 )
	 * 3. 지급 완료 처리
	 */
	public void dailyAllowancePayment(Member member, PaymentDailyRequestDto request) {
		PaymentRequestDto paymentRequestDto = paymentConverter.toRequest(member, request);

		// 금일 기준 중복 참여 확인
		dailyAllowanceValidate(paymentRequestDto);

		// 지급 등록

	}

	private void dailyAllowanceValidate(PaymentRequestDto request) {
		// 1. 데일리 용돈 금액 확인
		// 0 원 이상이여 합니다.
		boolean amountCheck = isAmountLessThanZero(request.getPaymentAmount());

		// 2. 금일 참여 확인
		boolean duplicateCheck = hasDuplicatePayment(request);

		if (duplicateCheck || amountCheck) {
			// 지급 불가
		}

	}

	/**
	 * 데일리 용돈 금액 검증
	 * amount <= 0 : true
	 * amount > 0 : false
	 */
	private boolean isAmountLessThanZero(int amount) {
		return amount <= 0;
	}

	/**
	 * 중복 참여 검증
	 * 참여 O : true
	 * 쳠여 X : false
	 */
	private boolean hasDuplicatePayment(PaymentRequestDto request) {
		List<PaymentResponseDto> result = paymentService.searchDuplicatePayment(request);
		return !result.isEmpty();
	}

	/**
	 * [ payment ] - 미션 도전
	 * 미션 검증 필요
	 * 1. 미션 기간 및 운영 상태 확인
	 *
	 * 지급 검증 필요
	 * 1. 금액 ( 미션 금액과 지급 금액 확인 )
	 * 2. 미션 중복 지급 여부 확인
	 *
	 * 지급 처리 - Payment 영역
	 * 1. 지급 내역 등록 및 히스토리 등록
	 * 2. 혜택 지급 - 입금 모듈 호출 ( 주석으로 표시 )
	 * 3. 지급 완료 처리
	 */
	public void missionPayment(Member member, PaymentMissionRequestDto request) {
		PaymentRequestDto paymentRequestDto = paymentConverter.toRequest(member, request);
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
