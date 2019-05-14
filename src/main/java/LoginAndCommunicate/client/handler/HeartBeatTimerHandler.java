package LoginAndCommunicate.client.handler;

import LoginAndCommunicate.packet.request.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @Author: pyh
 * @Date: 2019/5/14 19:15
 * @Version: 1.0
 * @Function:
 * @Description:
 *  客户端定时发送心跳
 */
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {

    // 实现了每隔 5 秒，向服务端发送一个心跳数据包，这个时间段通常要比服务端的空闲检测时间的一半要短一些，
    // 我们这里直接定义为空闲检测时间的三分之一，主要是为了排除公网偶发的秒级抖动。
    private static final int HEARTBEAT_INTERVAL = 5;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    //定时发送心跳
    private void scheduleSendHeartBeat(final ChannelHandlerContext ctx){
        ctx.executor().schedule(new Runnable() {
            @Override
            public void run() {
                if (ctx.channel().isActive()) {
                    ctx.writeAndFlush(new HeartBeatRequestPacket());
                    HeartBeatTimerHandler.this.scheduleSendHeartBeat(ctx);
                }

            }
        },HEARTBEAT_INTERVAL, TimeUnit.SECONDS);//返回的是当前的 channel 绑定的 NIO 线程
    }
}
