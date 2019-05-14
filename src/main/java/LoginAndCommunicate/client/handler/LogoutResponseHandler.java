package LoginAndCommunicate.client.handler;

import LoginAndCommunicate.packet.response.LogoutResponsePacket;
import LoginAndCommunicate.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @Author: pyh
 * @Date: 2019/5/14 8:18
 * @Version: 1.0
 * @Function:
 * @Description:
 *  登出响应处理器
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
