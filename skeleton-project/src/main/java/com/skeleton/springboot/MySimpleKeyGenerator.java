package com.skeleton.springboot;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class MySimpleKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object target, Method method, Object... params) {
		return generateKey(params);

	}


	/**
	 * Generate a key based on the specified parameters.
	 */
	public static Object generateKey(Object... params) {
		if (params.length == 0) {
			return "";
		}
		if (params.length == 1) {
			Object param = params[0];
			if (param != null && !param.getClass().isArray()) {
				return param;
			}
		}
		return "";
	}

}
