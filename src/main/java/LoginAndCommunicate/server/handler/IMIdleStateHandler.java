package LoginAndCommunicate.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * @Author: pyh
 * @Date: 2019/5/14 18:57
 * @Version: 1.0
 * @Function:
 * @Description:
 *
 *      检测假死连接之后的逻辑，继承netty自带类
 *      心跳检测，防止假死
 */
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler(){
        //第一个表示读空闲时间，指的是在这段时间内如果没有数据读到，就表示连接假死；
        // 第二个是写空闲时间，指的是 在这段时间如果没有写数据，就表示连接假死；
        // 第三个参数是读写空闲时间，表示在这段时间内如果没有产生数据读或者写，就表示连接假死。
        // 写空闲和读写空闲为0，表示我们不关心者两类条件；最后一个参数表示时间单位
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();//关闭
    }
}
