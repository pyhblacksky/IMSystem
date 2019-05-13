package LoginAndCommunicate.packet;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.MESSAGE_RESPONSE;

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

    private String fromUserId;//哪个用户发送来的
    private String fromUserName;

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

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
}
