package com.fingard.xuesl.im.serialize;

/**
 * @author xuesl
 * @date 2018/10/16
 */
public class ProtobufSerializer implements Serializer {
    @Override
    public byte getSerializerType() {
        return SerializerType.PROTOBUF;
    }

    @Override
    public byte[] serialize(Object object) {
        return new byte[0];
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        return null;
    }
}
