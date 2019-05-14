package LoginAndCommunicate.server.handler;

import LoginAndCommunicate.packet.request.MessageRequestPacket;
import LoginAndCommunicate.packet.response.MessageResponsePacket;
import LoginAndCommunicate.session.Session;
import LoginAndCommunicate.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: pyh
 * @Date: 2019/5/13 10:01
 * @Version: 1.0
 * @Function:
 * @Description:
 *  消息处理逻辑的处理器
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    //实现单聊
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        //1. 拿到消息发送方的会话信息
        Session session = SessionUtil.getSession(ctx.channel());

        //2. 通过消息发送方的会话信息构造要发送的消息
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());

        //3. 拿到消息接收方的channel
        Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        //4. 将消息发送给消息接收方
        //如果消息接收方当前是登录状态，直接发送，如果不在线，控制台打印出一条警告消息。
        if(toUserChannel != null && SessionUtil.hasLogin(toUserChannel)){
            toUserChannel.writeAndFlush(messageResponsePacket);
        } else{
            System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
        }
    }


        //常规测试方法
//    @Override
//    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
//        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
//        System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
//        messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
//
//        ctx.channel().writeAndFlush(messageResponsePacket);
//    }


}
