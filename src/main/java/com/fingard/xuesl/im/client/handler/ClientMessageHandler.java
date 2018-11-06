package com.fingard.xuesl.im.client.handler;

import com.fingard.xuesl.im.protocol.request.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xuesl
 * @date 2018/11/6
 */
public class ClientMessageHandler extends SimpleChannelInboundHandler<MessageResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponse messageResponse) {
        System.out.println(new Date() + "收到服务端回复: " + messageResponse.getMessage());
    }
}
