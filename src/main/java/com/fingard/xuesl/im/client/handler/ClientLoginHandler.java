package com.fingard.xuesl.im.client.handler;

import com.fingard.xuesl.im.protocol.request.LoginRequest;
import com.fingard.xuesl.im.protocol.request.LoginResponse;
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
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("客户端连接成功，开始登录...");
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserId("xuesl");
        loginRequest.setUserName("Cloud");
        loginRequest.setPassword("xshlxshl");

        ctx.channel().writeAndFlush(loginRequest);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponse loginResponse) {
        if (loginResponse.isSuccess()) {
            LoginUtil.login(channelHandlerContext.channel());
            log.info("登录成功！");
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
