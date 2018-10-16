package com.fingard.xuesl.im.server.handler;

import com.fingard.xuesl.im.protocol.request.LoginRequest;
import com.fingard.xuesl.im.protocol.request.LoginResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xuesl
 * @date 2018/10/16
 */
@Slf4j
public class ServerLoginHandler extends SimpleChannelInboundHandler<LoginRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequest loginRequest) {
        String userId = loginRequest.getUserId();
        String userName = loginRequest.getUserName();
        String password = loginRequest.getPassword();
        log.info("用户[" + userName + "]正在登录...");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserId(userId);
        loginResponse.setUserName(userName);
        if ("xuesl".equals(userId) && "xshlxshl".equals(password)) {
            loginResponse.setSuccess(true);
            channelHandlerContext.channel().writeAndFlush(loginResponse);
        } else {
            loginResponse.setSuccess(false);
            loginResponse.setInfo("用户名或密码错误！");
        }
        channelHandlerContext.channel().writeAndFlush(loginResponse).addListener(future -> {
            if (future.isSuccess()) {
                log.info("消息发送成功！");
            } else {
                log.info("消息发送失败！原因：" + future.cause().getMessage());
            }
        });
    }
}
