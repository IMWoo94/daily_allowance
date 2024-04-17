package com.daily.allowance.common.api;

import com.daily.allowance.common.code.CodeIfs;
import com.daily.allowance.common.code.SuccessCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseCode {

	private Integer responseCode;
	private String message;
	private String description;

	public static ResponseCode OK() {
		return ResponseCode.builder()
			.responseCode(SuccessCode.OK.getResponseCode())
			.message(SuccessCode.OK.getMessage())
			.description(SuccessCode.OK.getDescription())
			.build();
	}

	public static ResponseCode OK(CodeIfs codeIfs) {
		return ResponseCode.builder()
			.responseCode(codeIfs.getResponseCode())
			.message(codeIfs.getMessage())
			.description(codeIfs.getDescription())
			.build();
	}

	public static ResponseCode OK(CodeIfs codeIfs, String description) {
		return ResponseCode.builder()
			.responseCode(codeIfs.getResponseCode())
			.message(codeIfs.getMessage())
			.description(description)
			.build();
	}

	public static ResponseCode ERROR(CodeIfs codeIfs) {
		return ResponseCode.builder()
			.responseCode(codeIfs.getResponseCode())
			.message(codeIfs.getMessage())
			.description(codeIfs.getDescription())
			.build();
	}

	public static ResponseCode ERROR(CodeIfs codeIfs, Throwable throwable) {
		return ResponseCode.builder()
			.responseCode(codeIfs.getResponseCode())
			.message(codeIfs.getMessage())
			.description(throwable.getLocalizedMessage())
			.build();
	}

	public static ResponseCode ERROR(CodeIfs codeIfs, String description) {
		return ResponseCode.builder()
			.responseCode(codeIfs.getResponseCode())
			.message(codeIfs.getMessage())
			.description(description)
			.build();
	}

}
