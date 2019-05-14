package LoginAndCommunicate.packet.request;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.HEARTBEAT_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/14 19:22
 * @Version: 1.0
 * @Function:
 * @Description:
 *  心跳检测请求包
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
