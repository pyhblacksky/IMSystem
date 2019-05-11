package myProtocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import myProtocol.impl.JSONSerializer;
import myProtocol.impl.LoginRequestPacket;
import myProtocol.Packet;
import myProtocol.Serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: pyh
 * @Date: 2019/5/11 21:19
 * @Version: 1.0
 * @Function:
 * @Description:
 *  编码 和 解码相关类
 */
import static myProtocol.Command.LOGIN_REQUEST;

public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0x12345678;//自定义魔数
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializerAlgorithm(), serializer);
    }


    /**
     * 编码;封装成二进制的过程
     * */
    public ByteBuf encode(Packet packet){
        // 1. 创建ByteBuf对象
        // ioBuffer() 方法会返回适配 io 读写相关的内存，它会尽可能创建一个直接内存，
        // 直接内存可以理解为不受 jvm 堆管理的内存空间，写到 IO 缓冲区的效果更高。
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();

        // 2. 序列化Java对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);

        // 3. 实际编码过程,按照我们规定的协议方式，详见.md文件
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);//数据

        return byteBuf;
    }

    /**
     * 解码：解析Java对象的过程
     * */
    public Packet decode(ByteBuf byteBuf){
        //跳过magic number
        byteBuf.skipBytes(4);

        //跳过版本号
        byteBuf.skipBytes(1);

        //序列化算法标识
        byte serializeAlgorithm = byteBuf.readByte();

        //指令
        byte command = byteBuf.readByte();

        //数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if(requestType != null && serializer != null){
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }



    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }
}
