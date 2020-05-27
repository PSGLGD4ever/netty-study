package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.request.CreateGroupRequestPacket;
import com.beinglee.nettystudy.protocol.response.CreateGroupResponsePacket;
import com.beinglee.nettystudy.utils.IDUtils;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) {
        List<String> userIds = createGroupRequestPacket.getUserIds();
        List<String> userNames = new ArrayList<>(userIds.size());

        ChannelGroup channels = new DefaultChannelGroup(ctx.executor());
        userIds.forEach(
                userId -> {
                    Channel channel = SessionUtils.getChannel(userId);
                    if (channel != null) {
                        channels.add(channel);
                        userNames.add(SessionUtils.getSession(channel).getUserName());
                    }
                }
        );
        CreateGroupResponsePacket responsePacket = new CreateGroupResponsePacket();
        responsePacket.setGroupId(IDUtils.randomId());
        responsePacket.setSuccess(true);
        responsePacket.setUserNames(userNames);

        channels.writeAndFlush(responsePacket);

        System.out.print("群创建成功，id 为[" + responsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + responsePacket.getUserNames());
    }
}
