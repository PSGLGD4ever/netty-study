package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.packet.LoginResponsePacket;
import com.beinglee.nettystudy.utils.LocalDateUtils;
import com.beinglee.nettystudy.utils.LoginUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class ClientLoginHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端开始登录...");
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setUserId(UUID.randomUUID().toString());
        loginPacket.setUserName("beingLee");
        loginPacket.setPassword("p@ssword");
        ctx.channel().writeAndFlush(loginPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket responsePacket) {
        if (responsePacket.getSuccess()) {
            System.out.println(LocalDateUtils.now() + ":客户端登录成功");
            LoginUtils.markAsLogin(ctx.channel());
        } else {
            System.out.println(LocalDateUtils.now() + ":客户端登录失败，失败原因：" + responsePacket.getReason());
        }
    }
}
