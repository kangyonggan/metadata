package com.kangyonggan.metadata.service.impl;

import com.kangyonggan.metadata.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author kangyonggan
 * @since 2016/12/2
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    public boolean set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
        return Boolean.TRUE.booleanValue();
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param value
     * @return
     */
    public boolean set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        return Boolean.TRUE.booleanValue();
    }

    /**
     * set
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    public boolean set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
        return Boolean.TRUE.booleanValue();
    }

    /**
     * get
     *
     * @param key
     * @return
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * multiGet
     *
     * @param keys
     * @return
     */
    public List<Object> multiGet(Set<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * delete
     *
     * @param key
     * @return
     */
    public Object delete(String key) {
        Object object = redisTemplate.opsForValue().get(key);
        if (object != null) {
            redisTemplate.delete(key);
        }
        return object;
    }

    /**
     * delete all like pattern
     *
     * @param pattern
     * @return
     */
    public void deleteAll(String pattern) {
        redisTemplate.delete(redisTemplate.keys(pattern));
    }

    /**
     * incr
     *
     * @param key
     * @return
     */
    public long incr(String key) {
        return redisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * listLeftPush
     *
     * @param key
     * @return
     */
    public long listLeftPush(String key, String url) {
        long size = redisTemplate.opsForList().leftPush(key, url);
        return size;
    }

    /**
     * listRightPush
     *
     * @param key
     * @return
     */
    public long listRightPush(String key, String url) {
        long size = redisTemplate.opsForList().rightPush(key, url);
        return size;
    }

    /**
     * listRange
     *
     * @param key
     * @return
     */
    public List<Object> listRange(String key, long start, long end) {
        List<Object> list = redisTemplate.opsForList().range(key, start, end);
        return list;
    }

    /**
     * hashSetNx
     *
     * @param hash
     * @param key
     * @param value
     * @return
     */
    public boolean hashSetNx(String hash, String key, String value) {
        return redisTemplate.opsForHash().putIfAbsent(hash, key, value);
    }

    /**
     * hashSize
     *
     * @param hash
     * @return
     */
    public long hashSize(String hash) {
        return redisTemplate.opsForHash().size(hash);
    }

    /**
     * hashExist
     *
     * @param hash
     * @param key
     * @return
     */
    public boolean hashExist(String hash, String key) {
        return redisTemplate.opsForHash().hasKey(hash, key);
    }

    @Override
    public Set<String> getKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }
}
