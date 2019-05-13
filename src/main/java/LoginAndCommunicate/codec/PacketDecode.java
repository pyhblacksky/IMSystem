package LoginAndCommunicate.codec;

import LoginAndCommunicate.myProtocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author: pyh
 * @Date: 2019/5/13 10:35
 * @Version: 1.0
 * @Function:
 * @Description:
 *  解码处理器
 *
 *  解耦合
 */
public class PacketDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
