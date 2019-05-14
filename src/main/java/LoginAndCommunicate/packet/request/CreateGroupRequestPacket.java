package LoginAndCommunicate.packet.request;

import LoginAndCommunicate.myProtocol.Packet;

import java.util.List;

import static LoginAndCommunicate.myProtocol.Command.CREATE_GROUP_REQUEST;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:21
 * @Version: 1.0
 * @Function:
 * @Description:
 *  创建群组请求的包
 */
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }
}
