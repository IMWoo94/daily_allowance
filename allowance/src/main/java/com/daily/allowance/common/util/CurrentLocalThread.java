package com.daily.allowance.common.util;

import java.util.HashMap;
import java.util.Map;

public class CurrentLocalThread {

	private static final ThreadLocal<Map<String, Object>> INFO = new ThreadLocal<>();

	public static void init() {
		INFO.set(new HashMap<>());
	}

	public static Map<String, Object> getMap() {
		return INFO.get();
	}

	public static Object getValue(String key) {
		Map<String, Object> map = INFO.get();
		return map.getOrDefault(key, -1);
	}

	public static void set(String key, Object value) {
		Map<String, Object> map = INFO.get();
		map.put(key, value);
	}

	public static void remove() {
		INFO.remove();
	}

}
