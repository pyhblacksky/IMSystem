package LoginAndCommunicate.client.handler;

import LoginAndCommunicate.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/13 10:27
 * @Version: 1.0
 * @Function:
 * @Description:
 *  消息回复处理器
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    //单聊消息处理
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUserName = messageResponsePacket.getFromUserName();
        System.out.println(fromUserId + ":" + fromUserName + " -> " + messageResponsePacket .getMessage());
    }

    //一般的测试方法
//    @Override
//    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
//        System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
//    }
}
