package com.fingard.xuesl.im.client.console;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xuesl
 * @date 2018/12/11
 */
@Slf4j
public class ConsoleCommandManager implements ConsoleCommand{
    private Map<String, ConsoleCommand> consoleCommandMap = new HashMap<>();

    public ConsoleCommandManager() {
        consoleCommandMap.put("login", new LoginConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("sendToGroup", new SendToGroupConsoleCommand());
    }

    @Override
    public void execute(Scanner scanner, Channel channel) {
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if (consoleCommand != null) {
            consoleCommand.execute(scanner, channel);
        } else {
            log.error("无法识别输入的命令[{}]", command);
        }
    }
}
