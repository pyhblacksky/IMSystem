package LoginAndCommunicate.packet.response;

import LoginAndCommunicate.myProtocol.Packet;
import LoginAndCommunicate.session.Session;

import static LoginAndCommunicate.myProtocol.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/14 14:15
 * @Version: 1.0
 * @Function:
 * @Description:
 *  群消息响应包
 */
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }

    public String getFromGroupId() {
        return fromGroupId;
    }

    public void setFromGroupId(String fromGroupId) {
        this.fromGroupId = fromGroupId;
    }

    public Session getFromUser() {
        return fromUser;
    }

    public void setFromUser(Session fromUser) {
        this.fromUser = fromUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
