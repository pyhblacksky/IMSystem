package LoginAndCommunicate.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @Author: pyh
 * @Date: 2019/5/13 11:06
 * @Version: 1.0
 * @Function:
 * @Description:
 *  拆包粘包 Demo
 *
 *  从服务端的控制台输出可以看出，存在三种类型的输出
 *
 * 一种是正常的字符串输出。
 * 一种是多个字符串“粘”在了一起，我们定义这种 ByteBuf 为粘包。
 * 一种是一个字符串被“拆”开，形成一个破碎的包，我们定义这种 ByteBuf 为半包。
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    //生成1000个数据包
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 1000; i++){
            ByteBuf buffer = getByteBuf(ctx);
            ctx.channel().writeAndFlush(buffer);
        }
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "你好，继续学习，这里是拆包粘包测试！！！Netty方便又快捷！".getBytes(Charset.forName("utf-8"));
        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);

        return buffer;
    }
}
