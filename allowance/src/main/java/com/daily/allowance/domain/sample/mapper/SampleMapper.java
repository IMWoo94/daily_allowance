package com.daily.allowance.domain.sample.mapper;

import java.util.List;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.sample.dao.SampleDao;

@MyBatisMapper
public interface SampleMapper {

	List<SampleDao> findAllSample();
}
