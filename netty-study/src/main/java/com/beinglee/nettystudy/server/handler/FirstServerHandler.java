package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.utils.LocalDateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author zhanglu
 * @date 2020/5/22 17:46
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 服务端收到数据
        ByteBuf buffer = (ByteBuf) msg;
        System.out.println(LocalDateUtils.now() + "-服务端接受到数据 -> " + buffer.toString(StandardCharsets.UTF_8));
        // 服务端写出数据
        System.out.println(LocalDateUtils.now() + "-服务端写出数据...");
        byte[] bytes = "你好，小可爱，我是服务端，我收到你的消息啦~".getBytes(StandardCharsets.UTF_8);
        ByteBuf response = ctx.alloc().buffer();
        response.writeBytes(bytes);
        ctx.channel().writeAndFlush(response);
    }
}
