package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleManager implements ConsoleCommand {

    private final Map<String, ConsoleCommand> consoleCommandMap;

    public static final ConsoleManager INSTANCE = new ConsoleManager();

    public ConsoleManager() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        if (!SessionUtils.hasLogin(channel)) {
            return;
        }
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand == null) {
            System.err.println("无法识别[" + command + "]指令，请重新输入!");
            return;
        }
        consoleCommand.exec(scanner, channel);
    }
}
