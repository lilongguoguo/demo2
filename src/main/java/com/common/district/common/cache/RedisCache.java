package com.common.district.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;

/**
 * Description：
 * Created by Mars on 2017/3/8.
 */
public class RedisCache implements Cache {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCache.class);

    private static final long EXPIRE_TIME = 60;//单位：秒

    private RedisTemplate<String, Object> redisTemplate;
    private String name;
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        // TODO Auto-generated method stub
        return this.redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        final String keyf =  key.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] key = keyf.getBytes(Charset.defaultCharset());
                byte[] value = connection.get(key);
                if (value == null) {
                    return null;
                }
                return toObject(value);
            }
        });
//        LOGGER.info("查询redis缓存，key:{}，返回:{}", key, object);
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    @Override
    public void put(Object key, Object value) {
//        LOGGER.info("设置redis缓存，key：{}，value：{}", key, value);
        final String keyf = key.toString();
        final Object valuef = value;
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                byte[] keyb = keyf.getBytes(Charset.defaultCharset());
                byte[] valueb = toByteArray(valuef);
                connection.set(keyb, valueb);
                if (EXPIRE_TIME > 0) {
                    connection.expire(keyb, EXPIRE_TIME);
                }
                return 1L;
            }
        });
    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }

    @Override
    public void evict(Object key) {
        // TODO Auto-generated method stub
        System.out.println("del key");
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                return connection.del(keyf.getBytes(Charset.defaultCharset()));
            }
        });
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        System.out.println("clear key");
        redisTemplate.execute(new RedisCallback<String>() {
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        return null;
    }
}
