package LoginAndCommunicate.packet.response;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.QUIT_GROUP_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/14 8:33
 * @Version: 1.0
 * @Function:
 * @Description:
 *      退出群组响应的数据包
 */
public class QuitGroupResponsePacket extends Packet {

    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
