package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.PacketCodeC;
import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.packet.LoginResponsePacket;
import com.beinglee.nettystudy.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

public class ServerLoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf loginRequest = (ByteBuf) msg;
        Packet packet = PacketCodeC.getInstance().decode(loginRequest);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket login = (LoginRequestPacket) packet;
            String userName = login.getUserName();
            String password = login.getPassword();
            LoginResponsePacket response = new LoginResponsePacket();
            if (StringUtils.equals(userName, "beingLee") && StringUtils.equals(password, "p@ssword")) {
                response.setSuccess(true);
                String re = "客户端[" + userName + "]登录成功";
                System.out.println(re);
            } else {
                response.setSuccess(false);
                response.setReason("用户名密码错误!");
            }
            ctx.channel().writeAndFlush(PacketCodeC.getInstance().encode(response));
        }
    }
}
