package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.protocol.request.HeartBeatRequest;
import com.fingard.xuesl.im.protocol.response.HeartBeatResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class ServerHeartBeatHandler extends SimpleChannelInboundHandler<HeartBeatRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequest msg) throws Exception {
//        ctx.writeAndFlush(new HeartBeatResponse());
    }
}
