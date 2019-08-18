package com.common.district.common.utils;

import org.apache.commons.lang3.StringUtils;


public class CacheConfig {// 设置为静态变量，考虑以后单独启个线程定时从配置文件读取此值，快速响应缓存挂掉
    public static Integer cache;

    // 缓存Key前缀，目前可以是组织ID
    public static String keyPrefix;

    public static Integer defaultExpire;

    static {
        cache = 1;
        keyPrefix = "PDE";
        defaultExpire = 3600;
    }
    public CacheConfig(Integer cache, String keyPrefix, Integer defaultExpire) {
        if (cache == null) {
            CacheConfig.cache = 1;
        } else {
            CacheConfig.cache = cache;
        }
        if (defaultExpire == null) {
            CacheConfig.defaultExpire = 3600;
        } else {
            CacheConfig.defaultExpire = defaultExpire;
        }
        if (StringUtils.isEmpty(keyPrefix)) {
            CacheConfig.keyPrefix = "";
        } else {
            CacheConfig.keyPrefix = keyPrefix + ":";
        }
    }

	public static Integer getCache() {
		return cache;
	}
	public static void setCache(Integer cache) {
		CacheConfig.cache = cache;
	}
	public static String getKeyPrefix() {
		return keyPrefix;
	}
	public static void setKeyPrefix(String keyPrefix) {
		CacheConfig.keyPrefix = keyPrefix;
	}
	public static Integer getDefaultExpire() {
		return defaultExpire;
	}
	public static void setDefaultExpire(Integer defaultExpire) {
		CacheConfig.defaultExpire = defaultExpire;
	}

}
