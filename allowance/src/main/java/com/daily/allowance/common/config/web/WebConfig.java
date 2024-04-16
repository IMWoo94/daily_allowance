package com.daily.allowance.common.config.web;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.daily.allowance.common.config.web.resolver.MemberResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final MemberResolver memberResolver;

	public WebConfig(MemberResolver memberResolver) {
		this.memberResolver = memberResolver;
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(memberResolver);
	}
}
