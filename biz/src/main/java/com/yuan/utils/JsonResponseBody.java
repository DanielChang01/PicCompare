package com.yuan.utils;

import java.lang.annotation.*;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 用于标注JSON或JSONP请求。
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonResponseBody {

	String callback() default "callback";

	JsonGenerator.Feature[] enabledGens() default {};

	JsonGenerator.Feature[] disabledGens() default {};

	SerializationFeature[] enabledSers() default {};

	SerializationFeature[] disabledSers() default {};

}
