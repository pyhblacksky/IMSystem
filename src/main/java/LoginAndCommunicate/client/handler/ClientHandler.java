package LoginAndCommunicate.client.handler;

import LoginAndCommunicate.util.LoginUtil;
import LoginAndCommunicate.packet.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import LoginAndCommunicate.myProtocol.Packet;
import LoginAndCommunicate.myProtocol.PacketCodeC;
import LoginAndCommunicate.packet.request.LoginRequestPacket;
import LoginAndCommunicate.packet.response.LoginResponsePacket;

import java.util.Date;
import java.util.UUID;

/**
 * @Author: pyh
 * @Date: 2019/5/12 10:47
 * @Version: 1.0
 * @Function:
 * @Description:
 *  客户端逻辑处理器
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    //客户端处理登录请求
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + " : 客户端开始登录");

        //创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUserName("pyh");
        loginRequestPacket.setPassword("pwd");

        //编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        //写数据
        ctx.channel().writeAndFlush(buffer);
    }

    //客户端处理收到报文
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;

        Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if(loginResponsePacket.isSuccess()){
                LoginUtil.markAsLogin(ctx.channel());//设置登录标志位
                System.out.println(new Date() + " : 客户端登录成功");
            } else{
                System.out.println(new Date() + " : 客户端登录失败，原因："+ loginResponsePacket.getReason());
            }
        } else if(packet instanceof MessageResponsePacket){
            //客户端收到服务端的消息回执
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + " : 收到服务端的消息 :" + messageResponsePacket.getMessage());
        }
    }
}
