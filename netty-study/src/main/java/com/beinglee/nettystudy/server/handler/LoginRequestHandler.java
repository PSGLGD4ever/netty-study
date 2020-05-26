package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.packet.LoginResponsePacket;
import com.beinglee.nettystudy.server.Session;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author Luz
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        LoginResponsePacket responsePacket = new LoginResponsePacket();
        responsePacket.setVersion(loginRequestPacket.getVersion());
        responsePacket.setUserName(loginRequestPacket.getUserName());
        if (valid(loginRequestPacket)) {
            String userId = randomUserId();
            Session session = Session.builder()
                    .userId(userId)
                    .userName(loginRequestPacket.getUserName())
                    .build();
            SessionUtils.bindSession(session, ctx.channel());
            responsePacket.setUserId(userId);
            responsePacket.setSuccess(true);
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录成功");
        } else {
            responsePacket.setSuccess(false);
            responsePacket.setReason("账号密码校验失败");
            System.out.println("[" + loginRequestPacket.getUserName() + "]登录失败");
        }
        ctx.channel().writeAndFlush(responsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    private String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtils.unBindSession(ctx.channel());
    }
}
