package LoginAndCommunicate.server.handler;

import LoginAndCommunicate.util.LoginUtil;
import LoginAndCommunicate.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Author: pyh
 * @Date: 2019/5/13 15:20
 * @Version: 1.0
 * @Function:
 * @Description:
 *  判断用户是否登陆的处理器
 */
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

    public static final AuthHandler INSTANCE = new AuthHandler();
    protected AuthHandler(){}

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!SessionUtil.hasLogin(ctx.channel())){
            ctx.channel().close();//未登录，关闭channel
        } else{
            ctx.pipeline().remove(this);
            super.channelRead(ctx, msg);
        }
    }

    //移除校验的逻辑
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (LoginUtil.hasLogin(ctx.channel())) {
            System.out.println("当前连接登录验证完毕，无需再次验证, AuthHandler 被移除");
        } else {
            System.out.println("无登录验证，强制关闭连接!");
        }
    }
}
