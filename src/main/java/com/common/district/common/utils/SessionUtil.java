package com.common.district.common.utils;

import java.util.UUID;

public class SessionUtil {
	public static synchronized String generateSessionId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

}
