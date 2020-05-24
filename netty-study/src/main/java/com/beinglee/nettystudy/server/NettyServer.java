package com.beinglee.nettystudy.server;

import com.beinglee.nettystudy.server.handler.FirstServerHandler;
import com.beinglee.nettystudy.server.handler.ServerLoginHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zhanglu
 * @date 2020/5/19 21:04
 */
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        // 监听端口，accept新连接的线程组
        NioEventLoopGroup masterGroup = new NioEventLoopGroup();
        // 用来处理每一条连接 进行数据读写的线程组
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap server = new ServerBootstrap();
        // 指定线程模型
        server.group(masterGroup, workerGroup)
                // 指定IO模型
                .channel(NioServerSocketChannel.class)
                // 指定在服务端启动过程中的一些逻辑
                .handler(new ChannelInitializer<NioServerSocketChannel>() {
                    @Override
                    protected void initChannel(NioServerSocketChannel channel) {
                        System.out.println("服务端启动中...");
                    }
                })
                // 指定连接读写处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel channel) {
                        System.out.println("客户端连接成功...");
                        channel.pipeline().addLast(new ServerLoginHandler());
                    }
                });
        bind(server, PORT);
    }

    private static void bind(final ServerBootstrap server, final int port) {
        server.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功！奥利给！");
            } else {
                System.out.println("端口[" + port + "]绑定失败！");
                bind(server, port + 1);
            }
        });
    }

}
