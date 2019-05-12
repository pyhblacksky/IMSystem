package ClientLogin.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import myProtocol.Packet;
import myProtocol.PacketCodeC;
import myProtocol.impl.LoginRequestPacket;
import myProtocol.impl.LoginResponsePacket;

import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/12 11:20
 * @Version: 1.0
 * @Function:
 * @Description:
 *  服务端逻辑处理器
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 服务端处理登录请求
     * */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(new Date() + ": 客户端开始登录……");
        ByteBuf requestByteBuf = (ByteBuf) msg;

        //解码
        Packet packet = PacketCodeC.INSTANCE.decode(requestByteBuf);

        //判断是否是登录请求的数据包
        if(packet instanceof LoginRequestPacket){
            // 登录流程
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;

            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());
            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + ": 登录成功!");
            } else {
                loginResponsePacket.setReason("账号密码校验失败");
                loginResponsePacket.setSuccess(false);
                System.out.println(new Date() + ": 登录失败!");
            }

            //编码,登录响应
            ByteBuf responseByteBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseByteBuf);
        }
    }

    //校验逻辑，校验账号密码是否正确
    private boolean valid(LoginRequestPacket loginRequestPacket){
        return true;
    }
}
