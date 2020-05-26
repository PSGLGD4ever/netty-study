package com.beinglee.nettystudy.client;

import com.beinglee.nettystudy.client.handler.LoginResponseHandler;
import com.beinglee.nettystudy.client.handler.MessageResponseHandler;
import com.beinglee.nettystudy.codec.NettySpliter;
import com.beinglee.nettystudy.codec.PacketDecoder;
import com.beinglee.nettystudy.codec.PacketEncoder;
import com.beinglee.nettystudy.protocol.packet.LoginRequestPacket;
import com.beinglee.nettystudy.protocol.packet.MsgRequestPacket;
import com.beinglee.nettystudy.utils.SessionUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanglu
 * @date 2020/5/19 21:04
 */
public class NettyClient {

    private static final int MAX_RETRY = 5;
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8000;


    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) {
                        channel.pipeline().addLast(new NettySpliter());
                        channel.pipeline().addLast(new PacketDecoder());
                        channel.pipeline().addLast(new LoginResponseHandler());
                        channel.pipeline().addLast(new MessageResponseHandler());
                        channel.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(final Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
            } else if (retry == 0) {
                System.out.println("连接失败!");
            } else {
                int order = (MAX_RETRY - retry) + 1;
                int delay = 1 << order;
                System.out.println(LocalDateTime.now() + "：连接失败，第" + order + "次重连...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private static void startConsoleThread(Channel channel) {
        Scanner sc = new Scanner(System.in);
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtils.hasLogin(channel)) {
                    System.out.println("请输入用户名登录: ");
                    String userName = sc.nextLine();
                    System.out.println("请输入密码:");
                    String password = sc.nextLine();
                    LoginRequestPacket login = new LoginRequestPacket();
                    login.setUserName(userName);
                    login.setPassword(password);
                    channel.writeAndFlush(login);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    String toUserId = sc.next();
                    String message = sc.next();
                    MsgRequestPacket msgRequestPacket = new MsgRequestPacket();
                    msgRequestPacket.setMessage(message);
                    msgRequestPacket.setToUserId(toUserId);
                    channel.writeAndFlush(msgRequestPacket);
                }
            }
        }).start();
    }
}
