package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.request.SendToGroupRequestPacket;
import com.beinglee.nettystudy.protocol.response.SendToGroupResponsePacket;
import com.beinglee.nettystudy.server.Session;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

@ChannelHandler.Sharable
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendToGroupRequestPacket> {

    public static final SendToGroupRequestHandler INSTANCE = new SendToGroupRequestHandler();

    protected SendToGroupRequestHandler() {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SendToGroupRequestPacket requestPacket) {
        String groupId = requestPacket.getGroupId();
        String message = requestPacket.getMessage();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        Session session = SessionUtils.getSession(ctx.channel());
        if (channelGroup != null && session != null) {
            SendToGroupResponsePacket responsePacket = new SendToGroupResponsePacket();
            responsePacket.setMessage(message);
            responsePacket.setSendUserId(session.getUserId());
            responsePacket.setSendUsrName(session.getUserName());
            channelGroup.writeAndFlush(responsePacket);
        }
    }
}
