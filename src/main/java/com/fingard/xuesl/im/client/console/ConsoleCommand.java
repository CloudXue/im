package com.fingard.xuesl.im.client.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xuesl
 * @date 2018/12/11
 */
public interface ConsoleCommand {
    void execute (Scanner scanner, Channel channel);
}
