package LoginAndCommunicate.packet.request;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.LIST_GROUP_MEMBERS_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/14 8:29
 * @Version: 1.0
 * @Function:
 * @Description:
 *  列举群组成员请求的包
 */
public class ListGroupMembersRequestPacket extends Packet {

    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
