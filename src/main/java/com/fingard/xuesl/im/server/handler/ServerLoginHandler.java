package com.fingard.xuesl.im.server.handler;

import cn.hutool.core.util.RandomUtil;
import com.fingard.xuesl.im.protocol.request.LoginRequest;
import com.fingard.xuesl.im.protocol.response.LoginResponse;
import com.fingard.xuesl.im.session.Session;
import com.fingard.xuesl.im.util.LoginUtil;
import com.fingard.xuesl.im.util.SessionUtil;
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
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest loginRequest) {
        String userName = loginRequest.getUserName();
        log.info("用户[{}]正在登录...", userName);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUserName(userName);

        if (checkPassword(loginRequest)) {
            loginResponse.setSuccess(true);
            loginResponse.setUserId(RandomUtil.simpleUUID());
            LoginUtil.login(ctx.channel());
            SessionUtil.bindSession(new Session(loginResponse.getUserId(), userName), ctx.channel());
            log.info("用户[{}]登录成功！", userName);
        } else {
            loginResponse.setSuccess(false);
            loginResponse.setInfo("密码校验失败！");
        }
        ctx.channel().writeAndFlush(loginResponse);
    }

    private boolean checkPassword(LoginRequest loginRequest) {
        return true;
    }
}
