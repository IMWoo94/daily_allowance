package com.daily.allowance.common.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InfoContext {

	private static final ThreadLocal<Map<String, Object>> info = new ThreadLocal<>();

	public static void init() {
		info.set(new HashMap<>());
	}

	public static void set(String key, Object value) {
		Map<String, Object> map = getInfo();
		map.put(key, value);
		info.set(map);
	}

	public static Map<String, Object> getInfo() {
		return info.get();
	}

	public static Object getValue(String key) {
		return info.get().getOrDefault(key, null);
	}

	public static boolean containsKey(String key) {
		Map<String, Object> map = getInfo();
		return map.containsKey(key);
	}

	public static void remove() {
		info.remove();
	}

}
