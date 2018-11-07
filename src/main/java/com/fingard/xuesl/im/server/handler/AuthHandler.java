package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author xuesl
 * @date 2018/11/7
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (LoginUtil.hasLogin(ctx.channel())) {
            ctx.pipeline().remove(this);
            ctx.fireChannelRead(msg);
        } else {
            ctx.channel().close();
        }
    }
}
