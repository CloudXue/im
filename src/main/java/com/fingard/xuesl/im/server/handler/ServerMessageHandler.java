package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.protocol.request.MessageRequest;
import com.fingard.xuesl.im.protocol.request.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xuesl
 * @date 2018/11/6
 */
public class ServerMessageHandler extends SimpleChannelInboundHandler<MessageRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequest messageRequest) {
        System.out.println(new Date() + ":收到客户端信息:" + messageRequest.getMessage());

        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("服务端回复[" + messageRequest.getMessage() + "]");
        channelHandlerContext.channel().writeAndFlush(messageResponse);
    }
}
