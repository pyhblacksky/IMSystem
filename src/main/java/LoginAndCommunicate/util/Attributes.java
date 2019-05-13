package LoginAndCommunicate.util;

import LoginAndCommunicate.session.Session;
import io.netty.util.AttributeKey;

/**
 * @Author: pyh
 * @Date: 2019/5/12 20:57
 * @Version: 1.0
 * @Function:
 * @Description:
 *
 *  定义是否登录成功的标志位
 */
public interface Attributes {

    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
