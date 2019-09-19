package com.fingard.xuesl.im.client.console;

import com.fingard.xuesl.im.protocol.request.MessageRequest;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("请输入接收用户ID和消息内容，以空格隔开:");

        MessageRequest messageRequest = new MessageRequest();
        messageRequest.setToUserId(scanner.next());
        messageRequest.setMessage(scanner.next());
        channel.writeAndFlush(messageRequest);
    }
}
