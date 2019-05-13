package LoginAndCommunicate.packet;

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

    private String toUserId;//要发送给的用户的id

    private String message;

    public MessageRequestPacket(String toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }
    public MessageRequestPacket(){}

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

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }
}
