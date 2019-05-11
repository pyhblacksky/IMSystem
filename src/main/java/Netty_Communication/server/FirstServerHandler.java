package Netty_Communication.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/11 15:51
 * @Version: 1.0
 * @Function:
 * @Description:
 *  服务端读取客户端数据
 *  服务端回数据给客户端
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    //服务端读取客户端数据,并回复
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + " : 服务端读取到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

        //回复数据到客户端
        System.out.println(new Date() + " : 服务端写出数据");
        ByteBuf out = getByteBuf(ctx);
        ctx.channel().writeAndFlush(out);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        byte[] bytes = "你好，这里是服务端！".getBytes(Charset.forName("utf-8"));

        ByteBuf buffer = ctx.alloc().buffer();
        buffer.writeBytes(bytes);

        return buffer;
    }
}
