package com.daily.allowance.domain.errorlog.mapper;

import org.apache.ibatis.annotations.Insert;

import com.daily.allowance.common.annotation.MyBatisMapper;
import com.daily.allowance.domain.errorlog.dto.ErrorLogDto;

@MyBatisMapper
public interface ErrorLogMapper {

	@Insert("insert into error_log (error, url, create_date) values (#{error}, #{url}, current_date)")
	int registerErrorLog(ErrorLogDto dto);

}
