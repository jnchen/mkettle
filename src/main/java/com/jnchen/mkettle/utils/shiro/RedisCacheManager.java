package com.jnchen.mkettle.utils.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * Created by caojingchen on 2017/12/28.
 */
public class RedisCacheManager implements CacheManager{
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        return new ShiroCache<K, V>(s,redisTemplate);
    }

    public RedisTemplate<String,Object> getRedisTemplate(){
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
    }
}
