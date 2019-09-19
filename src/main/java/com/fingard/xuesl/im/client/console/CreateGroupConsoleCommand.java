package com.fingard.xuesl.im.client.console;

import cn.hutool.core.util.StrUtil;
import com.fingard.xuesl.im.protocol.request.CreateGroupRequest;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void execute(Scanner scanner, Channel channel) {
        System.out.println("输入要加入群聊的用户ID，以英文逗号隔开：");

        String userIds = scanner.next();

        //构建请求报文
        CreateGroupRequest request = new CreateGroupRequest();
        request.setUserIdList(Arrays.asList(userIds.split(StrUtil.COMMA)));

        channel.writeAndFlush(request);
    }
}
