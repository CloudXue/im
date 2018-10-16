package com.fingard.xuesl.im.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author xuesl
 * @date 2018/10/16
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerType() {
        return SerializerType.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
