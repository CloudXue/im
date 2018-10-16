package com.fingard.xuesl.im.codec;

import com.fingard.xuesl.im.protocol.AbstractPacket;
import com.fingard.xuesl.im.serialize.JavaSerializer;
import com.fingard.xuesl.im.serialize.SerializerType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author xuesl
 * @date 2018/10/16
 */
public class PacketEncoder extends MessageToByteEncoder<AbstractPacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AbstractPacket packet, ByteBuf byteBuf) {
        JavaSerializer javaSerializer = new JavaSerializer();

        byteBuf.writeInt(packet.getMagicNumber());
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(SerializerType.JAVA);
        byteBuf.writeByte(packet.getCommand());
        byte[] encode = javaSerializer.serialize(packet);
        byteBuf.writeInt(encode.length);
        byteBuf.writeBytes(encode);
    }
}
