package LoginAndCommunicate.util;

import LoginAndCommunicate.myProtocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @Author: pyh
 * @Date: 2019/5/13 14:17
 * @Version: 1.0
 * @Function:
 * @Description:
 *  分割器
 *
 *  拒绝非本协议连接。 使用自定义协议中的魔数来进行隔离
 *
 *  同时满足拆包的需求
 */
public class Spliter extends LengthFieldBasedFrameDecoder {

    /**
     * 注意，在协议中，由于存在拆包粘包的问题，需要使用netty自带的拆包器
     *
     * 关于拆包，我们只需要关注
     *
     * 在我们的自定义协议中，我们的长度域在整个数据包的哪个地方，
     *      专业术语来说就是长度域相对整个数据包的偏移量是多少，这里显然是 4+1+1+1=7。
     * 另外需要关注的就是，我们长度域的长度是多少，这里显然是 4。
     *      有了长度域偏移量和长度域的长度，我们就可以构造一个拆包器。
     *
     * 第一个参数指的是数据包的最大长度，
     * 第二个参数指的是长度域的偏移量，
     * 第三个参数指的是长度域的长度，
     * 这样一个拆包器写好之后，只需要在 pipeline 的最前面加上这个拆包器。
     * new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4);
     * */

    private static final int LENGTH_FIELD_OFFSET = 7;   //偏移量
    private static final int LENGTH_FIELD_LENGTH = 4;   //数据长度的存储域长度

    public Spliter(){
        super(Integer.MAX_VALUE, LENGTH_FIELD_OFFSET, LENGTH_FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //隔离非本协议的客户端
        if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
