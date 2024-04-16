package com.daily.allowance.common.api;

import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.code.SuccessCode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T, L> {

	// 응답 코드 및 Http 코드
	private CodeIfs responseCode;
	// 응답 결과
	private T result;
	// 요청 파라미터
	private L loopbackParam;

	public static <T, L> Api<T, L> OK() {
		var api = new Api<T, L>();
		api.responseCode = SuccessCode.OK;
		api.result = null;
		api.loopbackParam = null;
		return api;
	}

	public static <T, L> Api<T, L> OK(T result) {
		var api = new Api<T, L>();
		api.responseCode = SuccessCode.OK;
		api.result = result;
		api.loopbackParam = null;
		return api;
	}

	public static <T, L> Api<T, L> OK(T result, L loopbackParam) {
		var api = new Api<T, L>();
		api.responseCode = SuccessCode.OK;
		api.result = result;
		api.loopbackParam = loopbackParam;
		return api;
	}

	public static <T, L> Api<T, L> OK(T result, L loopbackParam, CodeIfs responseCode) {
		var api = new Api<T, L>();
		api.responseCode = responseCode;
		api.result = result;
		api.loopbackParam = loopbackParam;
		return api;
	}

}
