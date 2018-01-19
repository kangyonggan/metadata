package com.kangyonggan.metadata.shiro;

import com.kangyonggan.metadata.service.RedisService;
import com.kangyonggan.metadata.util.PropertiesUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * @author kangyonggan
 * @since 2016/12/31
 */
@Log4j2
public class MyEnterpriseCacheSessionDAO extends EnterpriseCacheSessionDAO {

    @Autowired
    private RedisService redisService;

    /**
     * redis键的前缀
     */
    private String prefix = PropertiesUtil.getProperties("redis.prefix") + ":";

    /**
     * 创建session，保存到redis数据库
     *
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        try {
            redisService.set(prefix + sessionId.toString(), session);
        } catch (Exception e) {
            log.warn("创建session保存到redis异常:{}", e.getMessage());
        }

        return sessionId;
    }

    /**
     * 获取session
     *
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
        // 先从缓存中获取session，如果没有再去数据库中获取
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            try {
                session = (Session) redisService.get(prefix + sessionId.toString());
            } catch (Exception e) {
                log.warn("从redis中获取session异常:{}", e.getMessage());
            }
        }
        return session;
    }

    /**
     * 更新session的最后一次访问时间
     *
     * @param session
     */
    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        try {
            redisService.set(prefix + session.getId().toString(), session);
        } catch (Exception e) {
            log.warn("更新redis中的session异常:{}", e.getMessage());
        }
    }

    /**
     * 删除session
     *
     * @param session
     */
    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        try {
            redisService.delete(prefix + session.getId().toString());
        } catch (Exception e) {
            log.warn("删除redis中的session异常:{}", e.getMessage());
        }
    }

}
