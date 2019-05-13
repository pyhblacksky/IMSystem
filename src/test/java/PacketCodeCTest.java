import io.netty.buffer.ByteBuf;
import LoginAndCommunicate.myProtocol.Packet;
import LoginAndCommunicate.myProtocol.PacketCodeC;
import LoginAndCommunicate.myProtocol.Serializer;
import LoginAndCommunicate.myProtocol.JSONSerializer;
import LoginAndCommunicate.packet.LoginRequestPacket;
import org.junit.Assert;
import org.junit.Test;

/**
 * @Author: pyh
 * @Date: 2019/5/11 21:57
 * @Version: 1.0
 * @Function:
 * @Description:
 *  关于Packet 解码  编码 的测试方法
 */
public class PacketCodeCTest {

    @Test
    public void encode(){

        Serializer serializer = new JSONSerializer();
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        loginRequestPacket.setVersion((byte)1);
        loginRequestPacket.setUserId("123");
        loginRequestPacket.setUserName("张三");
        loginRequestPacket.setPassword("password");

        PacketCodeC packetCodeC = PacketCodeC.INSTANCE;
        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
        Packet decodedPacket = packetCodeC.decode(byteBuf);

        //断言解码和编码是否匹配
        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));
    }

}
