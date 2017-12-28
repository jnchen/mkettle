package com.jnchen.mkettle.utils.shiro;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by caojingchen on 2017/12/28.
 */
public class ShiroCache<K,V> implements Cache<K,V> {
    private static final String REDIS_SHIRO_CACHE = "mkettle-shiro-cache:";
    private String cacheKey;
    private RedisTemplate<K,V> redisTemplate;
    private long globExpire = 30;

    public ShiroCache(String name,RedisTemplate client){
        this.cacheKey = REDIS_SHIRO_CACHE + name+":";
        this.redisTemplate = client;
    }

    @Override
    public V get(K k) throws CacheException {
        redisTemplate.boundValueOps(getCacheKey(k)).expire(globExpire, TimeUnit.MINUTES);
        return redisTemplate.boundValueOps(getCacheKey(k)).get();
    }

    @Override
    public V put(K k, V v) throws CacheException {
        V old = get(k);
        redisTemplate.boundValueOps(getCacheKey(k)).set(v);
        return old;
    }

    @Override
    public V remove(K k) throws CacheException {
        V old = get(k);
        redisTemplate.delete(getCacheKey(k));
        return old;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.delete(keys());
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return redisTemplate.keys(getCacheKey("*"));
    }

    @Override
    public Collection<V> values() {
        Set<K> set = keys();
        List<V> list = new ArrayList<>();
        for(K k :set){
            list.add(get(k));
        }
        return list;
    }

    private K getCacheKey(Object k){
        return (K)(this.cacheKey+k);
    }
}
