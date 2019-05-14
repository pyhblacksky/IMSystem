package LoginAndCommunicate.packet.request;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.JOIN_GROUP_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/14 8:23
 * @Version: 1.0
 * @Function:
 * @Description:
 *  加入群组的数据包
 */
public class JoinGroupRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
