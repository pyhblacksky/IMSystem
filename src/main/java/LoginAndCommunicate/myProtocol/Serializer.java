package LoginAndCommunicate.myProtocol;

import LoginAndCommunicate.myProtocol.impl.JSONSerializer;

/**
 * @Author: pyh
 * @Date: 2019/5/11 20:40
 * @Version: 1.0
 * @Function:
 * @Description:
 *  序列化接口
 */
public interface Serializer {

    /**
     * 定义一下序列化算法的类型以及默认序列化算法
     * */
    byte JSON_SERIALIZER = 1;
    Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     *  获取具体的序列化算法标识
     * */
    byte getSerializerAlgorithm();

    /**
     * Java对象转换为二进制
     *  将 Java 对象转换成字节数组
     * */
    byte[] serialize(Object object);

    /**
     * 二进制转换成Java对象
     *  将字节数组转换成某种类型的 Java 对象
     * */
    <T> T deserialize(Class<T> clazz, byte[] bytes);

}
