package com.daily.allowance.common.api;

import com.daily.allowance.common.code.CodeIfs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T, L> {

	// 응답 코드 및 Http 코드
	private ResponseCode responseCode;
	// 응답 결과
	private T result;
	// 요청 파라미터
	private L loopbackParam;

	public static Api<Object, Object> OK() {
		var api = new Api<Object, Object>();
		api.responseCode = ResponseCode.OK();
		return api;
	}

	public static <T> Api<T, Object> OK(T result) {
		var api = new Api<T, Object>();
		api.responseCode = ResponseCode.OK();
		api.result = result;
		return api;
	}

	public static <T, L> Api<T, L> OK(T result, L loopbackParam) {
		var api = new Api<T, L>();
		api.responseCode = ResponseCode.OK();
		api.result = result;
		api.loopbackParam = loopbackParam;
		return api;
	}

	public static <T, L> Api<T, L> OK(T result, L loopbackParam, CodeIfs responseCode) {
		var api = new Api<T, L>();
		api.responseCode = ResponseCode.OK(responseCode);
		api.result = result;
		api.loopbackParam = loopbackParam;
		return api;
	}

	public static <T, L> Api<T, L> OK(T result, L loopbackParam, CodeIfs responseCode, String description) {
		var api = new Api<T, L>();
		api.responseCode = ResponseCode.OK(responseCode, description);
		api.result = result;
		api.loopbackParam = loopbackParam;
		return api;
	}

	public static Api<Object, Object> ERROR(CodeIfs responseCode) {
		var api = new Api<Object, Object>();
		api.responseCode = ResponseCode.ERROR(responseCode);
		return api;
	}

	public static Api<Object, Object> ERROR(CodeIfs responseCode, Throwable throwable) {
		var api = new Api<Object, Object>();
		api.responseCode = ResponseCode.ERROR(responseCode, throwable);
		return api;
	}

	public static Api<Object, Object> ERROR(CodeIfs responseCode, String description) {
		var api = new Api<Object, Object>();
		api.responseCode = ResponseCode.ERROR(responseCode, description);
		return api;
	}

}
