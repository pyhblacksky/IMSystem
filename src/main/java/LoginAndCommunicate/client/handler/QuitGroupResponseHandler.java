package LoginAndCommunicate.client.handler;

import LoginAndCommunicate.packet.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: pyh
 * @Date: 2019/5/14 9:43
 * @Version: 1.0
 * @Function:
 * @Description:
 *  退出群响应处理器
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) throws Exception {
        if(responsePacket.isSuccess()){
            System.out.println("退出群【"+responsePacket.getGroupId()+"】成功");
        } else{
            System.out.println("退出群【"+responsePacket.getGroupId()+"】失败，原因为："+responsePacket.getReason());
        }
    }
}
