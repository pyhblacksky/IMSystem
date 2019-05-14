package LoginAndCommunicate.util;

import java.util.UUID;

/**
 * @Author: pyh
 * @Date: 2019/5/13 21:05
 * @Version: 1.0
 * @Function:
 * @Description:
 *  自定义UUID String 生成工具
 */
public class IDUtil {
    public static String randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
