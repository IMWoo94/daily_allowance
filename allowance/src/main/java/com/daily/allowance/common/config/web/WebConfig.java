package com.daily.allowance.common.config.web;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.daily.allowance.common.interceptor.InfoInterceptor;
import com.daily.allowance.common.resolver.MemberResolver;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final MemberResolver memberResolver;
	private final InfoInterceptor infoInterceptor;

	private List<String> DEFAULT_EXCLUDE = List.of(
		"/",
		"favicon.ico",
		"/error"
	);

	private List<String> SWAGGER = List.of(
		"/swagger-ui.html",
		"/swagger-ut/**",
		"/v3/api-docs/**"
	);

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(memberResolver);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(infoInterceptor)
			.addPathPatterns("/open-api/payment/**");
	}
}
