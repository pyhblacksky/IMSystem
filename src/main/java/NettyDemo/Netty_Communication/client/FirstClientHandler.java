package NettyDemo.Netty_Communication.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/11 15:36
 * @Version: 1.0
 * @Function:
 * @Description:
 *  客户端发数据到服务端
 *
 *  Netty 里面数据是以 ByteBuf 为单位的，
 *      所有需要写出的数据都必须塞到一个 ByteBuf，
 *      数据的写出是如此，数据的读取亦是如此
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {

    //这个方法会在客户端连接建立成功之后被调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " : 客户端写出数据");

        //1. 获取数据
        ByteBuf buffer = getByteBuf(ctx);

        //2. 写数据
        ctx.channel().writeAndFlush(buffer);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        //1.获取二进制抽象 ByteBuf
        ByteBuf buffer = ctx.alloc().buffer();//ctx.alloc() 获取到一个 ByteBuf 的内存管理器

        //2. 准备数据，指定字符串为 utf-8
        byte[] bytes = "你好，pyh！".getBytes(Charset.forName("utf-8"));

        //3. 填充数据到ByteBuf
        buffer.writeBytes(bytes);

        return buffer;
    }

    //客户端读取
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        System.out.println(new Date() + " : 客户端读取到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));
    }
}
