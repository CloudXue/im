package com.fingard.xuesl.im.client.handler;

import com.fingard.xuesl.im.protocol.request.HeartBeatRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class ClientHeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.executor().scheduleAtFixedRate(() -> {
            ctx.writeAndFlush(new HeartBeatRequest());
        }, HEARTBEAT_INTERVAL, HEARTBEAT_INTERVAL, TimeUnit.SECONDS);

        super.channelActive(ctx);
    }
}
