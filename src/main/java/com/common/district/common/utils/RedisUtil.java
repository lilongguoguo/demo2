package com.common.district.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;

import java.util.concurrent.TimeUnit;

public class RedisUtil {
	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	public static RedisTemplate redisTemplate = null;
	static {
		redisTemplate = SpringContextUtil.getApplicationContext().getBean(RedisTemplate.class);
	}

	/**
	 * 向缓存中设置字符串内容
	 * 
	 * @param key
	 *            key
	 * @param value
	 *            value
	 * @return
	 * @throws Exception
	 */
	public static boolean set(final String key, final String value) throws Exception {
		if (CacheConfig.cache == 0) {
            return false;
        }
        String keys = CacheConfig.keyPrefix + key;
        try {
			redisTemplate.opsForValue().set(keys, value);
		} catch (Exception e) {
			logger.error("向缓存中设置字符串内容error",e);
			return false;
		}
        return true;
	}

	/**
	 * 向缓存中设置对象
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static boolean set(final String key, final Object value) {
		if (CacheConfig.cache == 0) {
            return false;
        }
        String keys = CacheConfig.keyPrefix + key;
        try {
			redisTemplate.opsForValue().set(keys, value == null ? StringUtils.EMPTY : JSONObject.toJSONString(value));
		} catch (Exception e) {
			logger.error("向缓存中设置字符串内容error",e);
			return false;
		}
        return true;
	}
	/**
	 * 插入对象
	 * @param key
	 * @param value
	 * @param timeOut 单位s
	 * @return
	 * @return boolean
	 * @throws 
	 * @author QD.Interconnect -- SSK
	 *	       2018年8月17日下午4:48:32
	 */
	public static boolean set(String key,Object value,int timeOut){
		if (CacheConfig.cache == 0) {
            return false;
        }
        String keys = CacheConfig.keyPrefix + key;
        try {
			redisTemplate.opsForValue().set(keys, value == null ? StringUtils.EMPTY : JSONObject.toJSONString(value), value == null ? 10 : timeOut, TimeUnit.SECONDS);
		} catch (Exception e) {
			logger.error("向缓存中设置字符串内容error",e);
			return false;
		}
        return true;
	}
	
	/**
	 * 删除缓存中得对象，根据key
	 * 
	 * @param key
	 * @return
	 */
	public static boolean del(String key) {
		if (CacheConfig.cache == 0) {
            return false;
        }
        String keys = CacheConfig.keyPrefix + key;
        try {
			redisTemplate.opsForValue().set(keys, StringUtils.EMPTY);
		} catch (Exception e) {
			logger.error("向缓存中设置字符串内容error",e);
			return false;
		}
        return true;
	}

	/**
	 * 根据key 获取内容
	 * 
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		if (CacheConfig.cache == 0) {
            return null;
        }
        String keys = CacheConfig.keyPrefix + key;
        try {
			return redisTemplate.opsForValue().get(keys);
		} catch (Exception e) {
			logger.error("向缓存中设置字符串内容error",e);
			return false;
		}
	}

	/**
	 * 根据key 获取对象
	 * 
	 * @param key
	 * @return
	 */
	public static <T> T get(String key, Class<T> clazz) {
		try {
			if (CacheConfig.cache == 0) {
	            return null;
	        }
	        String keys = CacheConfig.keyPrefix + key;
			Object object = redisTemplate.opsForValue().get(keys);
			return JSON.parseObject(String.valueOf(object), clazz);
		} catch (Exception e) {
			return null;
		} 
	}

	public static Long increx(String key, int seconds) {
		try {
			RedisAtomicLong idCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
			Long value = idCounter.incrementAndGet();
			if(value== null || value == 1){
				idCounter.expire(seconds, TimeUnit.MILLISECONDS);
			}
			return value;
		} catch (Exception e) {
			logger.error("获取redis异常:key:{}" ,key, e);
			return null;
		}
	}
	public static Long incr(String key) {
		try {
			RedisAtomicLong idCounter = new RedisAtomicLong(key, redisTemplate.getConnectionFactory());
			Long value = idCounter.incrementAndGet();
			return value;
		} catch (Exception e) {
			logger.error("获取redis异常:key:{}" ,key, e);
			return null;
		}
	}
}
