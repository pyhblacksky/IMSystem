package LoginAndCommunicate.client;

import LoginAndCommunicate.SendAndReceive.LoginUtil;
import LoginAndCommunicate.SendAndReceive.MessageRequestPacket;
import LoginAndCommunicate.client.handler.ClientHandler;
import LoginAndCommunicate.client.handler.FirstClientHandler;
import LoginAndCommunicate.client.handler.LoginResponseHandler;
import LoginAndCommunicate.client.handler.MessageResponseHandler;
import LoginAndCommunicate.codec.PacketDecode;
import LoginAndCommunicate.codec.PacketEncoder;
import LoginAndCommunicate.spliter.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import LoginAndCommunicate.myProtocol.PacketCodeC;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Author: pyh
 * @Date: 2019/5/12 15:48
 * @Version: 1.0
 * @Function:
 * @Description:
 * Netty 客户端
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
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        //ch.pipeline().addLast(new ClientHandler());//自定义的处理器
                        //使用pipeLine()方式
                        /*拆包器*/
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecode());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                        //粘包测试
                        //ch.pipeline().addLast(new FirstClientHandler());
                    }
                });

        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

    private static void connect(final Bootstrap bootstrap, final String host, final int port, final int retry) {
        bootstrap.connect(host, port).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println(new Date() + ": 连接成功!");
                    Channel channel = ((ChannelFuture) future).channel();
                    //连接成功后，启动控制台线程
                    startConsoleThread(channel);
                } else if (retry == 0) {
                    System.err.println("重试次数已用完，放弃连接！");
                } else {
                    // 第几次重连
                    int order = (MAX_RETRY - retry) + 1;
                    // 本次重连的间隔
                    int delay = 1 << order;
                    System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                    bootstrap.config().group().schedule(new Runnable() {
                        @Override
                        public void run() {
                            connect(bootstrap, host, port, retry - 1);
                        }
                    }, delay, TimeUnit
                            .SECONDS);
                }
            }
        });
    }

    //启动控制台线程
    private static void startConsoleThread(final Channel channel){
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    while(!Thread.interrupted()){
                        if(LoginUtil.hasLogin(channel)){//是登录状态，允许控制台输入消息
                            System.out.println("输入消息发送至服务端：");
                            Scanner sc = new Scanner(System.in);
                            String line = sc.nextLine();

                            MessageRequestPacket packet = new MessageRequestPacket();
                            packet.setMessage(line);
                            ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channel.alloc(), packet);
                            channel.writeAndFlush(byteBuf);
                        }
                    }
                }
        }).start();
    }
}
