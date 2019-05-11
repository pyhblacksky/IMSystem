package NettyDemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @Author: pyh
 * @Date: 2019/5/11 14:38
 * @Version: 1.0
 * @Function:
 * @Description:
 *  使用Netty实现服务端
 *  Netty的好处：
 *  1. 使用 JDK 自带的NIO需要了解太多的概念，编程复杂，一不小心 bug 横飞
 *  2. Netty 底层 IO 模型随意切换，而这一切只需要做微小的改动，改改参数，Netty可以直接从 NIO 模型变身为 IO 模型
 *  3. Netty 自带的拆包解包，异常检测等机制让你从NIO的繁重细节中脱离出来，让你只需要关心业务逻辑
 *  4. Netty 解决了 JDK 的很多包括空轮询在内的 Bug
 *  5. Netty 底层对线程，selector 做了很多细小的优化，精心设计的 reactor 线程模型做到非常高效的并发处理
 *  6. 自带各种协议栈让你处理任何一种通用协议都几乎不用亲自动手
 *  7. Netty 社区活跃，遇到问题随时邮件列表或者 issue
 *  8. Netty 已经历各大 RPC 框架，消息中间件，分布式通信中间件线上的广泛验证，健壮性无比强大
 */
public class NettyServer {

    //要启动一个Netty服务端，必须要指定三类属性，分别是1.线程模型、2.IO 模型、3.连接读写处理逻辑
    public static void main(String[] args){

        ServerBootstrap serverBootstrap = new ServerBootstrap();//引导类，引导服务端启动工作

        //boss 对应 IOServer.java 中的接受新连接线程，主要负责创建新连接
        NioEventLoopGroup boss = new NioEventLoopGroup();
        //worker 对应 IOServer.java 中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理
        NioEventLoopGroup worker = new NioEventLoopGroup();

        serverBootstrap
                .group(boss, worker)    //给引导类配置两大线程组
                .channel(NioServerSocketChannel.class)  //指定服务端IO模型为NIO
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        //业务处理逻辑
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                System.out.println(msg);//打印数据
                            }
                        });
                    }
                })
                .bind(8000)//本地绑定8000端口
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if(future.isSuccess()){
                            System.out.println("端口绑定成功");
                        } else{
                            System.out.println("端口绑定失败");
                        }
                    }
                });
    }

    //自动绑定递增端口,可直接启动
    private static void bind(final ServerBootstrap serverBootstrap, final int port){
        serverBootstrap.bind(port).addListener(
                new GenericFutureListener<Future<? super Void>>() {
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if(future.isSuccess()){
                            System.out.println("端口[" + port + "]绑定成功!");
                        } else{
                            System.out.println("端口[" + port + "]绑定失败!");
                            bind(serverBootstrap, port+1);//绑定失败，端口号+1
                        }
                    }
                }
        );
    }

}
