package com.daily.allowance.domain.sample.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.daily.allowance.domain.sample.dao.SampleDao;

@Mapper
@Repository
public interface SampleMapper {

	List<SampleDao> findAllSample();
}
