package LoginAndCommunicate.server.handler;

import LoginAndCommunicate.packet.request.QuitGroupRequestPacket;
import LoginAndCommunicate.packet.response.QuitGroupResponsePacket;
import LoginAndCommunicate.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @Author: pyh
 * @Date: 2019/5/14 9:39
 * @Version: 1.0
 * @Function:
 * @Description:
 *  退出群请求的处理器
 */
@ChannelHandler.Sharable
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {

    public static final QuitGroupRequestHandler INSTANCE = new QuitGroupRequestHandler();
    protected QuitGroupRequestHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) throws Exception {
        //1. 获取群对应的channelGroup，然后将当前用户的channel移除
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.remove(ctx.channel());

        //2. 构造退群响应发送给客户端
        QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

        responsePacket.setSuccess(true);
        responsePacket.setGroupId(requestPacket.getGroupId());
        ctx.channel().writeAndFlush(responsePacket);
    }
}
