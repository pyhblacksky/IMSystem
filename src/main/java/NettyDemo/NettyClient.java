package NettyDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author: pyh
 * @Date: 2019/5/11 14:45
 * @Version: 1.0
 * @Function:
 * @Description:
 *  netty 实现的客户端部分
 */
public class NettyClient {
    static int MAX_RETRY = 5;//最大重连尝试次数
    public static void main(String[] args) throws InterruptedException{
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap
                .group(group)   //1. 指定线程模型
                .channel(NioSocketChannel.class)    // 2.指定IO类型为NIO
                .handler(new ChannelInitializer<Channel>() {    //3. IO处理器
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        //4. 建立连接
        constructConnect(bootstrap, "baidu.com",80, MAX_RETRY);

        //自测用
        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();

        while(true){
            channel.writeAndFlush(new Date() + " : hello world!");
            Thread.sleep(2000);
        }
    }

    //
    private static void  constructConnect(final Bootstrap bootstrap, final String host, final int port, final int retry){
        bootstrap
                .connect(host, port)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            System.out.println("连接建立成功");
                        } else if(retry == 0) {
                            System.err.println("重试次数用完，放弃连接！");
                        }  else{
                            //第几次重连
                            int order = (MAX_RETRY - retry) + 1;
                            //本次重连间隔
                            int delay = 1 << order;
                            System.err.println(new Date() + " ： 连接失败，第"+order+"次重连...");
                            bootstrap
                                    .config()
                                    .group()
                                    .schedule(new Runnable() {
                                        public void run() {
                                            constructConnect(bootstrap, host, port, retry - 1);
                                        }
                                    }, delay, TimeUnit.SECONDS);
                        }
                    }
                });
    }

}
