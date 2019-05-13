package LoginAndCommunicate.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import LoginAndCommunicate.myProtocol.Packet;
import LoginAndCommunicate.myProtocol.PacketCodeC;

/**
 * @Author: pyh
 * @Date: 2019/5/13 10:31
 * @Version: 1.0
 * @Function:
 * @Description:
 *  编码处理器
 *
 *  解耦合
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf out) throws Exception {
        PacketCodeC.INSTANCE.encode(out, packet);
    }
}
