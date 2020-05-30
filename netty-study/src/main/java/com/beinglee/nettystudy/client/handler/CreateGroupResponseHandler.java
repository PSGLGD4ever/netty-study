package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket responsePacket) {
        if (responsePacket.getSuccess() != null && responsePacket.getSuccess()) {
            System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
            System.out.println("群里面有：" + responsePacket.getUserNames());
        }
    }
}
