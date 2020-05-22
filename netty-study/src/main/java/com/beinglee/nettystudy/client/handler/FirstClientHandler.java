package com.beinglee.nettystudy.client.handler;

import com.beinglee.nettystudy.utils.LocalDateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * 客户端写入数据
 *
 * @author zhanglu
 * @date 2020/5/20 21:39
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(LocalDateUtils.now() + "-客户端写出数据...");
        ByteBuf buffer = ctx.alloc().buffer();
        byte[] bytes = "你好，小可爱".getBytes(StandardCharsets.UTF_8);
        buffer.writeBytes(bytes);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(LocalDateUtils.now() + "-客户端读到数据 -> " + byteBuf.toString(StandardCharsets.UTF_8));
    }
}
