package com.fingard.xuesl.im.client.handler;

import com.fingard.xuesl.im.protocol.response.LoginResponse;
import com.fingard.xuesl.im.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuesl
 * @date 2018/10/16
 */
@Slf4j
public class ClientLoginHandler extends SimpleChannelInboundHandler<LoginResponse> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponse loginResponse) {
        if (loginResponse.isSuccess()) {
            LoginUtil.login(channelHandlerContext.channel());
            log.info("登录成功！id为:" + loginResponse.getUserId());
        } else {
            log.info("登录失败，原因：" + loginResponse.getInfo());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接断开！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
