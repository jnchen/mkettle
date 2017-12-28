package com.jnchen.mkettle.utils.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * Created by caojingchen on 2017/12/28.
 */
public class RedisObjectSerializer implements RedisSerializer<Object>{
    private Converter<Object,byte[]> serializer = new SerializingConverter();
    private Converter<byte[],Object> deserializer = new DeserializingConverter();
    static final byte[] EMPTY_ARRA = new byte[0];

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        if(null == o){
            return EMPTY_ARRA;
        }
        try{
            return serializer.convert(o);
        }catch (Exception e){
            return EMPTY_ARRA;
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if(isEmpty(bytes)){
            return null;
        }
        try{
            return deserializer.convert(bytes);
        }catch (Exception e){
            throw new SerializationException("反序列化失败（取出对象失败)",e);
        }
    }

    private boolean isEmpty(byte[] data){
        return (null==data||0==data.length);
    }
}
