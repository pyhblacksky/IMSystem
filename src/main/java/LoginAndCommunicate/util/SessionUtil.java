package LoginAndCommunicate.util;

import LoginAndCommunicate.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

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

    //groupId -> channelGroup的映射
    private static final Map<String, ChannelGroup> groupIdChannelGroupMap = new ConcurrentHashMap<String, ChannelGroup>();

    //绑定session
    public static void bindSession(Session session, Channel channel){
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    //取消绑定session
    public static void unBindSession(Channel channel){
        if(hasLogin(channel)){
            Session session = getSession(channel);
            userIdChannelMap.remove(session.getUserId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + " 退出登录!");
        }
    }


    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
