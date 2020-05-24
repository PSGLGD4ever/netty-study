package com.beinglee.nettystudy.codec;

import com.beinglee.nettystudy.protocol.PacketCodeC;
import com.beinglee.nettystudy.protocol.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) {
        PacketCodeC.getInstance().encode(out, msg);
    }
}
