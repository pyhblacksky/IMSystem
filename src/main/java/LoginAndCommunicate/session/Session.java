package LoginAndCommunicate.session;

/**
 * @Author: pyh
 * @Date: 2019/5/13 15:51
 * @Version: 1.0
 * @Function:
 * @Description:
 * Session 对象，这个对象表示用户当前的会话信息，
 *  在我们这个应用程序里面，Session 只有两个字段
 */
public class Session {

    private String userId;//用户唯一性标志
    private String userName;

    public Session(String userId, String userName){
        this.userId = userId;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Session{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
