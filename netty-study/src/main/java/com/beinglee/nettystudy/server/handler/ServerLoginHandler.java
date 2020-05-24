package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.Packet;
import com.beinglee.nettystudy.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

public class ServerLoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf loginRequest = (ByteBuf) msg;
        Packet packet = PacketCodeC.getInstance().decode(loginRequest);
        if (packet instanceof LoginRequestPacket) {
            LoginRequestPacket login = (LoginRequestPacket) packet;
            String userName = login.getUserName();
            String password = login.getPassword();
            if (StringUtils.equals(userName, "beingLee") && StringUtils.equals(password, "p@ssword")) {
                String re = "客户端[" + userName + "]登录成功";
                System.out.println(re);
                ByteBuf response = ctx.alloc().buffer();
                response.writeBytes(re.getBytes(StandardCharsets.UTF_8));
                ctx.channel().writeAndFlush(response);
            }
        }
    }
}
