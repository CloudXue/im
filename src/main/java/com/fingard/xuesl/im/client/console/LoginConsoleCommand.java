package com.fingard.xuesl.im.client.console;

import com.fingard.xuesl.im.protocol.request.LoginRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        LoginRequest loginRequest = new LoginRequest();

        System.out.println("输入登录的用户名:");
        loginRequest.setUserName(scanner.nextLine());
        loginRequest.setPassword("暂时不校验");

        channel.writeAndFlush(loginRequest);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
