package com.daily.allowance.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Repository;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Mapper
@Repository
public @interface MyBatisMapper {

	@AliasFor(annotation = Repository.class)
	String value() default "";
}
