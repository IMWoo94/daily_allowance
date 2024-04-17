package com.daily.allowance.domain.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.common.api.Api;
import com.daily.allowance.common.code.ErrorCode;
import com.daily.allowance.domain.sample.business.SampleBusiness;

@RestController
@RequestMapping("/open-api/sample")
public class SampleController {

	private final SampleBusiness sampleBusiness;

	// 생성자 의존성 주입
	public SampleController(SampleBusiness sampleBusiness) {
		this.sampleBusiness = sampleBusiness;
	}

	@GetMapping("/ok")
	public Api ok() {
		var response = sampleBusiness.findAllSample();
		return Api.OK(response);
	}

	@GetMapping("/error")
	public Api error() {
		return Api.ERROR(ErrorCode.SERVER_ERROR, new RuntimeException("error 발생"));
	}
}
