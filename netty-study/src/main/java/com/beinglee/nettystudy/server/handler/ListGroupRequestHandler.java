package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.request.ListGroupRequestPacket;
import com.beinglee.nettystudy.protocol.response.ListGroupResponsePacket;
import com.beinglee.nettystudy.server.Session;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanglu
 * @date 2020/5/28 20:37
 */
public class ListGroupRequestHandler extends SimpleChannelInboundHandler<ListGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupRequestPacket requestPacket) {
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        List<Session> sessions = channelGroup.stream().map(SessionUtils::getSession).collect(Collectors.toList());
        ListGroupResponsePacket responsePacket = new ListGroupResponsePacket();
        responsePacket.setSessionList(sessions);
        responsePacket.setGroupId(groupId);
        ctx.channel().writeAndFlush(sessions);
    }
}
