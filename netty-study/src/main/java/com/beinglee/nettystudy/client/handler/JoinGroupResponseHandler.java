package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.response.JoinGroupResponsePacket;
import com.beinglee.nettystudy.server.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author zhanglu
 * @date 2020/5/29 11:51
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) {
        String groupId = responsePacket.getGroupId();
        List<Session> sessions = responsePacket.getSessions();
        System.out.println("群[" + groupId + "]现在的成员如下:" + sessions);
    }
}

