package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入用户名登录: ");
        String userName = sc.nextLine();
        LoginRequestPacket login = new LoginRequestPacket();
        login.setUserName(userName);
        channel.writeAndFlush(login);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}