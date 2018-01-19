package com.kangyonggan.metadata.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis服务接口
 *
 * @author kangyonggan
 * @since 2016/11/30
 */
public interface RedisService {

    /**
     * set
     *
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * set
     *
     * @param key
     * @param value
     * @param timeout
     * @return
     */
    boolean set(String key, Object value, long timeout);

    /**
     * set
     *
     * @param key
     * @param value
     * @param timeout
     * @param unit
     * @return
     */
    boolean set(String key, Object value, long timeout, TimeUnit unit);

    /**
     * get
     *
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * multiGet
     *
     * @param keys
     * @return
     */
    List<Object> multiGet(Set<String> keys);

    /**
     * delete
     *
     * @param key
     * @return
     */
    Object delete(String key);

    /**
     * delete all like pattern
     *
     * @param pattern
     */
    void deleteAll(String pattern);

    /**
     * incr
     *
     * @param key
     * @return
     */
    long incr(String key);

    /**
     * listLeftPush
     *
     * @param key
     * @return
     */
    long listLeftPush(String key, String url);

    /**
     * listRightPush
     *
     * @param key
     * @return
     */
    long listRightPush(String key, String url);

    /**
     * listRange
     *
     * @param key
     * @return
     */
    List<Object> listRange(String key, long start, long end);

    /**
     * hashSetNx
     *
     * @param hash
     * @param key
     * @param value
     * @return
     */
    boolean hashSetNx(String hash, String key, String value);

    /**
     * hashSize
     *
     * @param hash
     * @return
     */
    long hashSize(String hash);

    /**
     * hashExist
     *
     * @param hash
     * @param key
     * @return
     */
    boolean hashExist(String hash, String key);

    /**
     * get keys by pattern
     *
     * @param pattern
     * @return
     */
    Set<String> getKeys(String pattern);

}
