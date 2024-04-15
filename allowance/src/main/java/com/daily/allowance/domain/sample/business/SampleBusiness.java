package com.daily.allowance.domain.sample.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.domain.sample.converter.SampleConverter;
import com.daily.allowance.domain.sample.dto.SampleResponseDto;
import com.daily.allowance.domain.sample.service.SampleService;

@Business
public class SampleBusiness {

	private final SampleService sampleService;
	private final SampleConverter sampleConverter;

	public SampleBusiness(SampleService sampleService, SampleConverter sampleConverter) {
		this.sampleService = sampleService;
		this.sampleConverter = sampleConverter;
	}

	public List<SampleResponseDto> findAllSample() {
		var samples = sampleService.findAllSample();
		return samples.stream()
			.map(sampleConverter::toResponse)
			.toList();
	}

}
