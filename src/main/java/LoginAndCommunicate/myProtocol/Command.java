package LoginAndCommunicate.myProtocol;

/**
 * @Author: pyh
 * @Date: 2019/5/11 20:31
 * @Version: 1.0
 * @Function:
 * @Description:
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
