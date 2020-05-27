package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.response.MsgResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MsgResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgResponsePacket responsePacket) {
        String fromUserId = responsePacket.getFromUserId();
        String fromUserName = responsePacket.getFromUserName();
        String message = responsePacket.getMessage();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + message);
    }
}
