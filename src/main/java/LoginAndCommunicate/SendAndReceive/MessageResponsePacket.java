package LoginAndCommunicate.SendAndReceive;

import myProtocol.Packet;

import static myProtocol.Command.MESSAGE_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/12 20:55
 * @Version: 1.0
 * @Function:
 * @Description:
 *
 *  服务端发送至客户端的消息对象
 */
public class MessageResponsePacket extends Packet {

    private String message;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
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
