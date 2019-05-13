package LoginAndCommunicate.myProtocol.impl;

import LoginAndCommunicate.myProtocol.Packet;

import static LoginAndCommunicate.myProtocol.Command.LOGIN_RESPONSE;

/**
 * @Author: pyh
 * @Date: 2019/5/12 15:26
 * @Version: 1.0
 * @Function:
 * @Description:
 *  登录相应包
 */
public class LoginResponsePacket extends Packet {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    @Override
    public Byte getVersion() {
        return super.getVersion();
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
