package com.beinglee.nettystudy.client.console;

import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner sc, Channel channel) {
        System.out.println("请输入用户名登录: ");
        String userName = sc.nextLine();
        System.out.println("请输入密码:");
        String password = sc.nextLine();
        LoginRequestPacket login = new LoginRequestPacket();
        login.setUserName(userName);
        login.setPassword(password);
        channel.writeAndFlush(login);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
