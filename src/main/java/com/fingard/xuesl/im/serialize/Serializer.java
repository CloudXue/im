package com.fingard.xuesl.im.serialize;

/**
 * @author xuesl
 * @date 2018/10/15
 */
public interface Serializer {
//    Serializer DEFAULT

    byte getSerializerType();

    byte[] serialize(Object object);

    <T> T deserialize(byte[] bytes, T clazz);
}
