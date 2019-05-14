package LoginAndCommunicate.packet.request;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.QUIT_GROUP_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/14 8:31
 * @Version: 1.0
 * @Function:
 * @Description:
 *  退出群组请求的包
 */
public class QuitGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
