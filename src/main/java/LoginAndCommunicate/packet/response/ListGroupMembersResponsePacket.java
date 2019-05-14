package LoginAndCommunicate.packet.response;

import LoginAndCommunicate.myProtocol.Packet;
import LoginAndCommunicate.session.Session;

import java.util.List;

import static LoginAndCommunicate.myProtocol.Command.LIST_GROUP_MEMBERS_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/14 9:15
 * @Version: 1.0
 * @Function:
 * @Description:
 *  成员列表响应的包
 */
public class ListGroupMembersResponsePacket extends Packet {

    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Session> getSessionList() {
        return sessionList;
    }

    public void setSessionList(List<Session> sessionList) {
        this.sessionList = sessionList;
    }
}
