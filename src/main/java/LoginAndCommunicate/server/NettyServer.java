package LoginAndCommunicate.server;

import LoginAndCommunicate.codec.PacketDecode;
import LoginAndCommunicate.codec.PacketEncoder;
import LoginAndCommunicate.server.handler.AuthHandler;
import LoginAndCommunicate.server.handler.LifeCycleTestHandler;
import LoginAndCommunicate.server.handler.LoginRequestHandler;
import LoginAndCommunicate.server.handler.MessageRequestHandler;
import LoginAndCommunicate.spliter.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/12 15:47
 * @Version: 1.0
 * @Function:
 * @Description:
 *  Netty 服务端
 *
 */
public class NettyServer {

    private static final int PORT = 8000;

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        final ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap
                .group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        //ch.pipeline().addLast(new ServerHandler());//自定义的 Handle

                        //生命周期测试
                        //ch.pipeline().addLast(new LifeCycleTestHandler());

                        //使用pipLine()的方式， 登录和发送消息
                        /*拆包器，netty自带*/
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecode());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new AuthHandler());//新增用户认证的handler
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                        //粘包测试
                        //ch.pipeline().addLast(new FirstServerHandler());
                    }
                });


        bind(serverBootstrap, PORT);
    }

    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                }
            }
        });
    }

}
