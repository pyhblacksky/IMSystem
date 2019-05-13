package LoginAndCommunicate.util;

import LoginAndCommunicate.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: pyh
 * @Date: 2019/5/13 16:00
 * @Version: 1.0
 * @Function:
 * @Description:
 *  Session工具类
 *
 *      登陆时保存会话，登出时删除会话信息
 */
public class SessionUtil {
    //userId -> channel的映射
    private static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<String, Channel>();

    //绑定session
    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    //取消绑定session
    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }


    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
