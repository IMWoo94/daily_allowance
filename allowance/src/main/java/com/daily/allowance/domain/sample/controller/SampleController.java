package com.daily.allowance.domain.sample.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daily.allowance.domain.sample.business.SampleBusiness;
import com.daily.allowance.domain.sample.dto.SampleResponseDto;

@RestController
@RequestMapping("/open-api/sample")
public class SampleController {

	private final SampleBusiness sampleBusiness;

	// 생성자 의존성 주입
	public SampleController(SampleBusiness sampleBusiness) {
		this.sampleBusiness = sampleBusiness;
	}

	@GetMapping
	public List<SampleResponseDto> findAllSample() {
		return sampleBusiness.findAllSample();
	}
}
