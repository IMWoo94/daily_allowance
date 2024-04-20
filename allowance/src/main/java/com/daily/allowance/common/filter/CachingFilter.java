package com.daily.allowance.common.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.daily.allowance.common.model.InfoContext;
import com.daily.allowance.common.util.CachedBodyHttpServletRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CachingFilter implements Filter {

	private final ObjectMapper objectMapper;

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
		IOException,
		ServletException {

		/**
		 * TODO
		 * request 정보를 재사용하기 위해서 재사용 Wrapper 를 만들어 사용.
		 * 기존의 ContentCachingRequestWrapper 를 이용하면 response 재사용은 가능하지만 request 는 불가하여 커스텀
		 * ...한가지 문제는 h2 Console 기능이 활성화가 되지 않음.
		 * getParameter 정보 등이 구현되지 않아 그런 문제로 보이며, 차후 알아보기
		 * https://imwoo94.notion.site/H2-Console-No-suitable-driver-found-for-08001-0-4f85bae124c245c3ae12777875a8c726?pvs=4
		 */
		CachedBodyHttpServletRequest req = new CachedBodyHttpServletRequest(
			(HttpServletRequest)servletRequest);

		// Thread Local 초기화
		InfoContext.init();

		// 요청 URL 등록
		InfoContext.set("url", req.getRequestURI());
		InfoContext.set("loopbackParameter", "");

		// 요청 파라미터 등록
		if (req.getContentLength() != 0) {
			if ("application/json".equals(req.getContentType())) {
				JsonNode jsonNode = objectMapper.readTree(req.getInputStream().readAllBytes());
				InfoContext.set("loopbackParameter", jsonNode);
			} else {
				InfoContext.set("loopbackParameter", req.getInputStream().readAllBytes());
			}
		}
		filterChain.doFilter(req, servletResponse);

		// Thread Local 삭제
		InfoContext.remove();
	}

}
