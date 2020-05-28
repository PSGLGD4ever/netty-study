package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.request.ListGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zhanglu
 * @date 2020/5/28 20:31
 */
public class ListGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupRequestPacket listGroupRequestPacket = new ListGroupRequestPacket();
        System.out.print("输入 groupId，获取群成员列表：");
        String groupId = scanner.next();
        listGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupRequestPacket);
    }
}
