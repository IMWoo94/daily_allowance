package com.daily.allowance.domain.errorlog.service;

import org.springframework.stereotype.Service;

import com.daily.allowance.domain.errorlog.dto.ErrorLogDto;
import com.daily.allowance.domain.errorlog.mapper.ErrorLogMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ErrorLogService {

	private final ErrorLogMapper errorLogMapper;

	public void registerErrorLog(ErrorLogDto dto) {
		errorLogMapper.registerErrorLog(dto);
	}
}
