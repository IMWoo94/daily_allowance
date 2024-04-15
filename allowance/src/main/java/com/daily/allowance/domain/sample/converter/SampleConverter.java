package com.daily.allowance.domain.sample.converter;

import java.util.Optional;

import com.daily.allowance.common.annotation.Converter;
import com.daily.allowance.domain.sample.dao.SampleDao;
import com.daily.allowance.domain.sample.dto.SampleResponseDto;

@Converter
public class SampleConverter {

	public SampleResponseDto toResponse(SampleDao response) {
		return Optional.ofNullable(response)
			.map(it -> {
				return SampleResponseDto.builder()
					.id(it.getId())
					.description(it.getDescription())
					.build();
			})
			.orElseThrow(() -> new RuntimeException("SampleConverter response null point"));
	}
}
