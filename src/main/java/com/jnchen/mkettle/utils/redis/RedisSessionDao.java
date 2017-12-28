package com.jnchen.mkettle.utils.redis;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;


/**
 * Created by caojingchen on 2017/12/28.
 */
@Component
public class RedisSessionDao extends EnterpriseCacheSessionDAO{
    private static Logger logger = LoggerFactory.getLogger(RedisSessionDao.class);
    //session 在redis中的过期时间30分钟 30*60s
    private static int expireTime = 1800;

    private static String prefix = "mkettle-shiro-session:";

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = super.doCreate(session);
        logger.debug("创建session:{}",session.getId());
        redisTemplate.opsForValue().set(prefix + sessionId.toString(),session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        logger.debug("获取session:{}",sessionId);
        Session session = super.doReadSession(sessionId);
        if(null==session){
            session = (Session)redisTemplate.opsForValue().get(prefix+sessionId.toString());
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        logger.debug("获取session:{}",session.getId());
        String key = prefix + session.getId().toString();
        if(!redisTemplate.hasKey(key)){
            redisTemplate.opsForValue().set(key,session);
        }
        redisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
    }

    @Override
    protected void doDelete(Session session) {
        logger.debug("删除session:{}",session.getId());
        super.doDelete(session);
        redisTemplate.delete(prefix + session.getId().toString());
    }
}
