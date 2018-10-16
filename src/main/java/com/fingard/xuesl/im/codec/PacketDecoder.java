package com.fingard.xuesl.im.codec;

import com.fingard.xuesl.im.protocol.request.LoginRequest;
import com.fingard.xuesl.im.serialize.JavaSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * @author xuesl
 * @date 2018/10/16
 */
public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        JavaSerializer javaSerializer = new JavaSerializer();
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte serializerType = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object obj = javaSerializer.deserialize(bytes, LoginRequest.class);
        list.add(obj);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
        cause.printStackTrace();
    }
}
