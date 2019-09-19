package com.fingard.xuesl.im.client.console;

import com.fingard.xuesl.im.protocol.request.LogoutRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class LogoutConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        channel.writeAndFlush(new LogoutRequest());
    }
}
