package LoginAndCommunicate.server.handler;

import LoginAndCommunicate.packet.request.ListGroupMembersRequestPacket;
import LoginAndCommunicate.packet.response.ListGroupMembersResponsePacket;
import LoginAndCommunicate.session.Session;
import LoginAndCommunicate.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: pyh
 * @Date: 2019/5/14 10:21
 * @Version: 1.0
 * @Function:
 * @Description:
 *  请求查看指定群组的所有成员的处理器
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
        //1. 获取群的ChannelGroup
        String groupId = requestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

        //2. 遍历群成员的channel，对应的session，构造群成员的信息
        List<Session> sessionList = new ArrayList<Session>();
        for(Channel channel : channelGroup){
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        //3. 构建获取成员列表响应写回客户端
        ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

        responsePacket.setGroupId(groupId);
        responsePacket.setSessionList(sessionList);
        ctx.channel().writeAndFlush(responsePacket);
    }
}
