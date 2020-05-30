package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.response.SendToGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendToGroupResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupResponsePacket responsePacket) {
        String message = responsePacket.getMessage();
        String sendUserId = responsePacket.getSendUserId();
        String sendUsrName = responsePacket.getSendUsrName();
        System.out.println("收到[" + sendUserId + ":" + sendUsrName + "]的消息，消息内容为[" + message + "]");
    }
}
