package myProtocol;

/**
 * @Author: pyh
 * @Date: 2019/5/11 20:45
 * @Version: 1.0
 * @Function:
 * @Description:
 *  在这使用最简单的 json 序列化方式，使用阿里巴巴的 fastjson 作为序列化框架。
 */
public interface SerializerAlgorithm {
    /**
     * Json 序列化
     * */
    byte JSON = 1;
}
