package LoginAndCommunicate.packet;

import LoginAndCommunicate.myProtocol.Packet;

import java.util.List;

import static LoginAndCommunicate.myProtocol.Command.CREATE_GROUP_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:27
 * @Version: 1.0
 * @Function:
 * @Description:
 *  创建群组响应的处理包
 */
public class CreateGroupResponsePacket extends Packet {

    private boolean success;

    private String groupId;

    private List<String> userNameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }
}
