package com.daily.allowance.domain.sample.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.sample.dto.SampleResponseDto;
import com.daily.allowance.domain.sample.mapper.SampleMapper;

@Service
public class SampleService {

	private final SampleMapper sampleMapper;

	public SampleService(SampleMapper sampleMapper) {
		this.sampleMapper = sampleMapper;
	}

	public List<SampleResponseDto> findAllSample() {
		return sampleMapper.findAllSample();
	}
}
