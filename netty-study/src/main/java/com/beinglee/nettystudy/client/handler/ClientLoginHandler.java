package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.PacketCodeC;
import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.packet.LoginResponsePacket;
import com.beinglee.nettystudy.protocol.packet.MsgResponsePacket;
import com.beinglee.nettystudy.protocol.packet.Packet;
import com.beinglee.nettystudy.utils.LocalDateUtils;
import com.beinglee.nettystudy.utils.LoginUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

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
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        Packet response = PacketCodeC.getInstance().decode((ByteBuf) msg);
        if (response instanceof LoginResponsePacket) {
            LoginResponsePacket re = (LoginResponsePacket) response;
            if (re.getSuccess()) {
                System.out.println(LocalDateUtils.now() + ":客户端登录成功");
                LoginUtils.markAsLogin(ctx.channel());
            } else {
                System.out.println(LocalDateUtils.now() + ":客户端登录失败，失败原因：" + re.getReason());
            }
        } else if (response instanceof MsgResponsePacket) {
            MsgResponsePacket msgPacket = (MsgResponsePacket) response;
            System.out.println(LocalDateUtils.now() + ":收到服务端的消息：" + msgPacket.getMessage());
        }
    }
}
