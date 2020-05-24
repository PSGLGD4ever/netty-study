package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.packet.MsgResponsePacket;
import com.beinglee.nettystudy.utils.LocalDateUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MsgResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgResponsePacket responsePacket) {
        System.out.println(LocalDateUtils.now() + ":收到服务端的消息：" + responsePacket.getMessage());
    }
}
