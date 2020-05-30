package com.beinglee.nettystudy.server.handler;

import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhanglu
 * @date 2020/5/25 17:21
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();

    protected AuthHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (SessionUtils.hasLogin(ctx.channel())) {
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        } else {
            ctx.channel().close();
        }
    }
}
