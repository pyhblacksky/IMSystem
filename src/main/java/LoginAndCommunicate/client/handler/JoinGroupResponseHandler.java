package LoginAndCommunicate.client.handler;

import LoginAndCommunicate.packet.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: pyh
 * @Date: 2019/5/14 9:36
 * @Version: 1.0
 * @Function:
 * @Description:
 *  加入群组响应处理器
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) throws Exception {
        if(responsePacket.isSuccess()){
            System.out.println("加入群【"+responsePacket.getGroupId()+"】成功");
        } else{
            System.out.println("加入群【"+responsePacket.getGroupId()+"】失败，原因为："+responsePacket.getReason());
        }
    }
}
