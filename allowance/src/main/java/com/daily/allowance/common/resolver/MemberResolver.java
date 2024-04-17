package com.daily.allowance.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.daily.allowance.common.annotation.Admin;
import com.daily.allowance.common.annotation.User;
import com.daily.allowance.common.model.Member;
import com.daily.allowance.common.model.Role;

@Component
public class MemberResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// @Member 어노테이션 확인 및 Member Class 확인
		boolean annotation = false;
		if (parameter.hasParameterAnnotation(Admin.class) ||
			parameter.hasParameterAnnotation(User.class)
		) {
			annotation = true;
		}
		boolean type = parameter.getParameter().getType().equals(Member.class);

		return (annotation && type);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

		// 앞 서버 단에서 사용자에 대한 정보가 들어온다는 가정하에 진행
		Member member = Member.builder()
			.memberId(1L)
			.memberName("라이언")
			.role(Role.USER)
			.build();

		if (parameter.hasParameterAnnotation(Admin.class)) {
			member.setRole(Role.ADMIN);
		}

		return member;
	}
}
