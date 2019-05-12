package myProtocol.impl;

/**
 * @Author: pyh
 * @Date: 2019/5/11 20:35
 * @Version: 1.0
 * @Function:
 * @Description:
 */

import myProtocol.Packet;

import static myProtocol.Command.LOGIN_REQUEST;

public class LoginRequestPacket extends Packet {

    //用户ID
    private String userId;

    //用户名
    private String userName;

    //密码
    private String password;

    @Override
    public Byte getCommand() {
        return LOGIN_REQUEST;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
