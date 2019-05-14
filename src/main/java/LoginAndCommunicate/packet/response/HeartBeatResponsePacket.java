package LoginAndCommunicate.packet.response;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.HEARTBEAT_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/14 19:24
 * @Version: 1.0
 * @Function:
 * @Description:
 *  心跳检测响应包
 */
public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
