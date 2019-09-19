package com.fingard.xuesl.im.codec;

import com.fingard.xuesl.im.protocol.Command;
import com.fingard.xuesl.im.protocol.request.LoginRequest;
import com.fingard.xuesl.im.protocol.response.LoginResponse;
import com.fingard.xuesl.im.serialize.JavaSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuesl
 * @date 2018/10/16
 */
public class PacketDecoder extends MessageToMessageDecoder<ByteBuf> {

    private Map<Byte, Class<?>> commandClasses = new HashMap<>();

    public PacketDecoder() {
        commandClasses.put(Command.LOGIN_REQUEST, LoginRequest.class);
        commandClasses.put(Command.LOGIN_RESPONSE, LoginResponse.class);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        JavaSerializer javaSerializer = new JavaSerializer();
        byteBuf.skipBytes(4);
        byteBuf.skipBytes(1);
        byte serializerType = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
//        System.out.println(length);
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Object obj = javaSerializer.deserialize(bytes, commandClasses.get(command));
        list.add(obj);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.channel().close();
        cause.printStackTrace();
    }
}
