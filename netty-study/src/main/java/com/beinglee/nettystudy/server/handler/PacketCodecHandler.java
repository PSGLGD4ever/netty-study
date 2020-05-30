package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.protocol.Packet;
import com.beinglee.nettystudy.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();

    protected PacketCodecHandler() {
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, List<Object> out) {
        ByteBuf buffer = ctx.channel().alloc().ioBuffer();
        PacketCodeC.getInstance().encode(buffer, packet);
        out.add(buffer);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) {
        out.add(PacketCodeC.getInstance().decode(byteBuf));
    }
}
