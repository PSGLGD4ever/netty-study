package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.packet.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    public static final String USER_ID_SPLIT = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.println("[拉人群聊]输入 userId 列表,逗号分割～");
        List<String> userIds = Arrays.asList(scanner.next().split(USER_ID_SPLIT));
        createGroupRequestPacket.setUserIds(userIds);
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
