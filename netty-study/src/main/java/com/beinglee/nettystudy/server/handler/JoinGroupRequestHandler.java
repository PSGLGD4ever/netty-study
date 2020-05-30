package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.request.JoinGroupRequestPacket;
import com.beinglee.nettystudy.protocol.response.JoinGroupResponsePacket;
import com.beinglee.nettystudy.server.Session;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhanglu
 * @date 2020/5/29 11:38
 */
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {

    public static final JoinGroupRequestHandler INSTANCE = new JoinGroupRequestHandler();

    protected JoinGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket requestPacket) {
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        if (channelGroup != null) {
            JoinGroupResponsePacket responsePacket = new JoinGroupResponsePacket();
            responsePacket.setGroupId(groupId);
            channelGroup.add(ctx.channel());
            List<Session> sessions = channelGroup.stream().map(SessionUtils::getSession).collect(Collectors.toList());
            responsePacket.setSessions(sessions);
            ctx.channel().writeAndFlush(responsePacket);
        }

    }
}
