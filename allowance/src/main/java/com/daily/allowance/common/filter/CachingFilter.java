package com.daily.allowance.common.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.daily.allowance.common.model.InfoContext;
import com.daily.allowance.common.util.CachedBodyHttpServletRequest;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CachingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {
		CachedBodyHttpServletRequest req = new CachedBodyHttpServletRequest(
			(HttpServletRequest)servletRequest);

		// Thread Local 초기화
		InfoContext.init();

		var loopbackParameter = new String(req.getInputStream().readAllBytes());

		// 요청 URL 등록
		InfoContext.set("url", req.getRequestURI());
		// 요청 파라미터 등록
		InfoContext.set("loopbackParameter", new String(req.getInputStream().readAllBytes()));

		filterChain.doFilter(req, servletResponse);

		// Thread Local 삭제
		InfoContext.remove();
	}

}
