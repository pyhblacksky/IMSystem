package LoginAndCommunicate.util;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Author: pyh
 * @Date: 2019/5/12 21:08
 * @Version: 1.0
 * @Function:
 * @Description:
 *  登录相关工具类
 *  LoginUtil 用于设置登录标志位以及判断是否有标志位
 */
public class LoginUtil {

    //设置登录标志位
    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    //判断是否已经登录，判断是否有标志位
    public static boolean hasLogin(Channel channel){
        Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

        return loginAttr.get() != null;
    }

}
