package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author zhanglu
 * @date 2020/5/29 13:55
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入要退出的群Id:");
        String quitGroupId = scanner.next();
        QuitGroupRequestPacket requestPacket = new QuitGroupRequestPacket();
        requestPacket.setGroupId(quitGroupId);
        channel.writeAndFlush(requestPacket);
    }
}
