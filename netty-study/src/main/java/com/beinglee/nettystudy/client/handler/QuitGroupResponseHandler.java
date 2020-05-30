package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.protocol.response.QuitGroupResponsePacket;
import com.beinglee.nettystudy.server.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * @author zhanglu
 * @date 2020/5/29 14:08
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) throws Exception {
        String groupId = responsePacket.getGroupId();
        List<Session> sessions = responsePacket.getSessions();
        System.out.println("有人退群！当前群[" + groupId + "]成员列表为:" + sessions);
    }
}
