package LoginAndCommunicate.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import LoginAndCommunicate.myProtocol.impl.LoginRequestPacket;
import LoginAndCommunicate.myProtocol.impl.LoginResponsePacket;

import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/13 9:58
 * @Version: 1.0
 * @Function:
 * @Description:
 *  简写登录处理逻辑
 *
 *  不用判断当前对象是否是本handler可以处理的对象。父类进行处理
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) {
        //登录逻辑
        System.out.println(new Date() + ": 收到客户端登录请求……");

        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        if (valid(loginRequestPacket)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登录成功!  欢迎您" + loginRequestPacket.getUserName());
        } else {
            loginResponsePacket.setReason("账号密码校验失败");
            loginResponsePacket.setSuccess(false);
            System.out.println(new Date() + ": 登录失败!");
        }

        // 登录响应
        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }
}
