package com.daily.allowance.domain.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.sample.dto.SampleResponseDto;

@MyBatisMapper
public interface SampleMapper {

	@Select("select s.id, s.description from sample s")
	List<SampleResponseDto> findAllSample();
}
