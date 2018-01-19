package com.kangyonggan.metadata.handle;

import com.kangyonggan.extra.core.handle.CacheHandle;
import com.kangyonggan.metadata.service.RedisService;
import com.kangyonggan.metadata.util.SpringUtils;

import java.util.concurrent.TimeUnit;

/**
 * @author kangyonggan
 * @since 2017/11/5 0005
 */
public class RedisCacheHandle implements CacheHandle {

    private RedisService redisService;

    @Override
    public Object set(String key, Object returnValue, Long expire) {
        getRedisService().set(key, returnValue, expire, TimeUnit.MILLISECONDS);
        return returnValue;
    }

    @Override
    public Object get(String key) {
        return getRedisService().get(key);
    }

    @Override
    public void delete(String... keys) {
        for (String key : keys) {
            getRedisService().delete(key);
        }
    }

    private RedisService getRedisService() {
        if (redisService == null) {
            redisService = SpringUtils.getBean(RedisService.class);
        }

        return redisService;
    }
}
