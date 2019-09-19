package com.fingard.xuesl.im.client.console;

import com.fingard.xuesl.im.protocol.request.GroupMessageRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个某个群组：");

        String toGroupId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new GroupMessageRequest(toGroupId, message));
    }
}
