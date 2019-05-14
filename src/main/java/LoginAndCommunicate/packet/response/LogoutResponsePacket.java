package LoginAndCommunicate.packet.response;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.LOGOUT_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:26
 * @Version: 1.0
 * @Function:
 * @Description:
 *  登出响应处理包
 */
public class LogoutResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
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
