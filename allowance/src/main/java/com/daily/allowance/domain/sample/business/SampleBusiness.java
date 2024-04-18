package com.daily.allowance.domain.sample.business;

import java.util.List;

import com.daily.allowance.common.annotation.Business;
import com.daily.allowance.domain.sample.converter.SampleConverter;
import com.daily.allowance.domain.sample.dto.SampleResponseDto;
import com.daily.allowance.domain.sample.service.SampleService;

import lombok.RequiredArgsConstructor;

@Business
@RequiredArgsConstructor
public class SampleBusiness {

	private final SampleService sampleService;
	private final SampleConverter sampleConverter;
	
	public List<SampleResponseDto> findAllSample() {
		return sampleService.findAllSample();
	}

}
