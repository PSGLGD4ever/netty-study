package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class ClientLoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端开始登录...");
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setUserId(UUID.randomUUID().toString());
        loginPacket.setUserName("beingLee");
        loginPacket.setPassword("p@ssword");
        ByteBuf login = PacketCodeC.getInstance().encode(loginPacket);
        ctx.channel().writeAndFlush(login);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf re = (ByteBuf) msg;
        System.out.println(re.toString(StandardCharsets.UTF_8));
    }
}
