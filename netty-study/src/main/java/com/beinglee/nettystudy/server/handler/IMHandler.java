package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.Command;
import com.beinglee.nettystudy.protocol.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet> {

    public static final IMHandler INSTANCE = new IMHandler();

    private final Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    protected IMHandler() {
        handlerMap = new HashMap<>();
        handlerMap.put(Command.LOGIN_REQUEST, LoginRequestHandler.INSTANCE);
        handlerMap.put(Command.MSG_REQUEST, MessageRequestHandler.INSTANCE);
        handlerMap.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.LIST_GROUP_REQUEST, ListGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.JOIN_GROUP_REQUEST, JoinGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.QUIT_GROUP_REQUEST, QuitGroupRequestHandler.INSTANCE);
        handlerMap.put(Command.SEND_TO_GROUP_REQUEST, SendToGroupRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet packet) throws Exception {
        SimpleChannelInboundHandler<? extends Packet> handler = handlerMap.get(packet.getCommand());
        if (handler != null) {
            handler.channelRead(ctx, packet);
        }
    }
}
