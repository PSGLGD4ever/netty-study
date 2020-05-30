package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.request.SendToGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要发送消息的群Id:");
        String groupId = scanner.next();
        System.out.println("请输入要发送的消息：");
        String message = scanner.next();
        SendToGroupRequestPacket requestPacket = new SendToGroupRequestPacket();
        requestPacket.setGroupId(groupId);
        requestPacket.setMessage(message);
        channel.writeAndFlush(requestPacket);
    }
}
