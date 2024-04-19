package com.daily.allowance.common.api;

import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.model.InfoContext;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

	// 응답 코드 및 Http 코드
	private ResponseCode responseCode;
	// 응답 결과
	private T result;
	// 요청 파라미터
	private Object loopbackParam;

	public static Api OK() {
		var api = new Api();
		api.responseCode = ResponseCode.OK();
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static <T> Api<T> OK(T result) {
		var api = new Api<T>();
		api.responseCode = ResponseCode.OK();
		api.result = result;
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static Api OK(CodeIfs responseCode) {
		var api = new Api();
		api.responseCode = ResponseCode.OK(responseCode);
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static <T> Api<T> OK(T result, CodeIfs responseCode) {
		var api = new Api<T>();
		api.responseCode = ResponseCode.OK(responseCode);
		api.result = result;
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static <T> Api<T> OK(T result, CodeIfs responseCode, String description) {
		var api = new Api<T>();
		api.responseCode = ResponseCode.OK(responseCode, description);
		api.result = result;
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static Api ERROR(CodeIfs responseCode) {
		var api = new Api();
		api.responseCode = ResponseCode.ERROR(responseCode);
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static Api ERROR(CodeIfs responseCode, Throwable throwable) {
		var api = new Api();
		api.responseCode = ResponseCode.ERROR(responseCode, throwable);
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	public static Api ERROR(CodeIfs responseCode, String description) {
		var api = new Api();
		api.responseCode = ResponseCode.ERROR(responseCode, description);
		api.loopbackParam = getLoopbackParmeter();
		return api;
	}

	private static Object getLoopbackParmeter() {
		return InfoContext.getValue("loopbackParameter");
	}

}
