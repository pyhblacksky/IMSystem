package LoginAndCommunicate.packet.request;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.LOGOUT_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:25
 * @Version: 1.0
 * @Function:
 * @Description:
 *  登出请求处理包
 */
public class LogoutRequestPacket extends Packet {

    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
