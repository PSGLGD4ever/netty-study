package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.PacketCodeC;
import com.beinglee.nettystudy.protocol.packet.*;
import com.beinglee.nettystudy.utils.LocalDateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.apache.commons.lang3.StringUtils;

public class ServerLoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf request = (ByteBuf) msg;
        Packet packet = PacketCodeC.getInstance().decode(request);
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
        } else if (packet instanceof MsgRequestPacket) {
            MsgRequestPacket msgRequest = (MsgRequestPacket) packet;
            System.out.println(LocalDateUtils.now() + ":收到客户端消息：" + msgRequest.getMessage());

            MsgResponsePacket response = new MsgResponsePacket();
            response.setMessage("服务端回复：[" + msgRequest.getMessage() + "]");
            ctx.channel().writeAndFlush(PacketCodeC.getInstance().encode(response));
        }
    }
}
