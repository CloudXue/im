package com.fingard.xuesl.im.client.handler;

import cn.hutool.core.util.StrUtil;
import com.fingard.xuesl.im.protocol.response.MessageResponse;
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
        System.out.println(StrUtil.format(new Date() + "收到{}的消息:{} " , messageResponse.getFromUserName(),messageResponse.getMessage()));
    }
}
