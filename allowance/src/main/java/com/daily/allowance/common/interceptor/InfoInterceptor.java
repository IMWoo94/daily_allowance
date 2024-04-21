package com.daily.allowance.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.daily.allowance.common.model.InfoContext;
import com.daily.allowance.domain.errorlog.dto.ErrorLogDto;
import com.daily.allowance.domain.errorlog.service.ErrorLogService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class InfoInterceptor implements HandlerInterceptor {

	private final ErrorLogService errorLogService;

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
		Exception ex) throws Exception {

		// log.info("InfoInterceptor afterCompletion : {}", InfoContext.getInfo());

		// 예외 정보 등록 등록
		if (InfoContext.containsKey("exception")) {
			ErrorLogDto dto = ErrorLogDto.builder()
				.error((String)InfoContext.getValue("exception"))
				.url((String)InfoContext.getValue("url"))
				.build();
			errorLogService.registerErrorLog(dto);
		}
	}
}
