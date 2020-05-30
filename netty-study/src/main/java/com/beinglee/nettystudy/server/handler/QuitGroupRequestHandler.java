package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.request.QuitGroupRequestPacket;
import com.beinglee.nettystudy.protocol.response.QuitGroupResponsePacket;
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
 * @date 2020/5/29 13:59
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();

    protected QuitGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) {
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtils.getChannelGroup(groupId);
        if (channelGroup != null) {
            channelGroup.remove(ctx.channel());
            QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();
            responsePacket.setGroupId(groupId);
            List<Session> sessions = channelGroup.stream().map(SessionUtils::getSession).collect(Collectors.toList());
            responsePacket.setSessions(sessions);
            channelGroup.writeAndFlush(responsePacket);
        }
    }
}
