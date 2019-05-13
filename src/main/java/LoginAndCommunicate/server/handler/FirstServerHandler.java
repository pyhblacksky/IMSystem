package LoginAndCommunicate.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/13 11:19
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
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + " : 服务器读取到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
