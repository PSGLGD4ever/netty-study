package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.packet.LoginResponsePacket;
import com.beinglee.nettystudy.utils.LocalDateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.commons.lang3.StringUtils;

public class ServerLoginHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        System.out.println(LocalDateUtils.now() + ":收到客户端登录请求...");

        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            responsePacket.setSuccess(true);
            System.out.println(LocalDateUtils.now() + ":客户端登录成功～");
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号密码校验失败");
            System.out.println(LocalDateUtils.now() + ":客户端登录失败");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        String userName = loginRequestPacket.getUserName();
        String password = loginRequestPacket.getPassword();
        return StringUtils.equals(userName, "beingLee") && StringUtils.equals(password, "p@ssword");
    }
}
