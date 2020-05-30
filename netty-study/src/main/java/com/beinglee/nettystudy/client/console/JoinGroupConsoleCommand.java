package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zhanglu
 * @date 2020/5/29 11:32
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要加入的群Id:");
        String joinGroupId = scanner.next();
        JoinGroupRequestPacket requestPacket = new JoinGroupRequestPacket();
        requestPacket.setGroupId(joinGroupId);
        channel.writeAndFlush(requestPacket);
    }
}
