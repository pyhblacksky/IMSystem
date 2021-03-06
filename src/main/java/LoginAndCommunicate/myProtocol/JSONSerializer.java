package LoginAndCommunicate.myProtocol;

import com.alibaba.fastjson.JSON;
import LoginAndCommunicate.myProtocol.Serializer;
import LoginAndCommunicate.myProtocol.SerializerAlgorithm;

/**
 * @Author: pyh
 * @Date: 2019/5/11 21:04
 * @Version: 1.0
 * @Function:
 * @Description:
 */
public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);//阿里的fastjson序列化框架
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

}
