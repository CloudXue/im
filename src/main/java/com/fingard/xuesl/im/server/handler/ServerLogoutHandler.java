package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.protocol.request.LogoutRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class ServerLogoutHandler extends SimpleChannelInboundHandler<LogoutRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequest msg) throws Exception {

    }
}
