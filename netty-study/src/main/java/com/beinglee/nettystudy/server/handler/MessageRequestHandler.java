package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.packet.MsgRequestPacket;
import com.beinglee.nettystudy.protocol.packet.MsgResponsePacket;
import com.beinglee.nettystudy.utils.LocalDateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MsgRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgRequestPacket msgRequestPacket) {
        MsgResponsePacket responsePacket = new MsgResponsePacket();
        System.out.println(LocalDateUtils.now() + ":收到客户端消息:" + msgRequestPacket.getMessage());
        responsePacket.setMessage("服务端回复[" + msgRequestPacket.getMessage() + "]");
        ctx.channel().writeAndFlush(responsePacket);
    }
}
