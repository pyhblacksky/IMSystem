package LoginAndCommunicate.SendAndReceive;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.MESSAGE_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/12 20:53
 * @Version: 1.0
 * @Function:
 * @Description:
 *
 *  客户端发送至服务端的消息对象
 */
public class MessageRequestPacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    @Override
    public Byte getVersion() {
        return super.getVersion();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
