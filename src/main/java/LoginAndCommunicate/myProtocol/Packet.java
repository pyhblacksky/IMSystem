package LoginAndCommunicate.myProtocol;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @Author: pyh
 * @Date: 2019/5/11 20:29
 * @Version: 1.0
 * @Function:
 * @Description:
 *  通信过程中的Java对象
 *
 *  是通信过程中 Java 对象的抽象类，定义了一个版本号（默认值为 1 ）以及一个获取指令的抽象方法，
 *  所有的指令数据包都必须实现这个方法，这样我们就可以知道某种指令的含义。
 */
public abstract class Packet {

    /**
     * 注意，在协议中，由于存在拆包粘包的问题，需要使用netty自带的拆包器
     *
     * 关于拆包，我们只需要关注
     *
     * 在我们的自定义协议中，我们的长度域在整个数据包的哪个地方，
     *      专业术语来说就是长度域相对整个数据包的偏移量是多少，这里显然是 4+1+1+1=7。
     * 另外需要关注的就是，我们长度域的长度是多少，这里显然是 4。
     *      有了长度域偏移量和长度域的长度，我们就可以构造一个拆包器。
     *
     * 第一个参数指的是数据包的最大长度，
     * 第二个参数指的是长度域的偏移量，
     * 第三个参数指的是长度域的长度，
     * 这样一个拆包器写好之后，只需要在 pipeline 的最前面加上这个拆包器。
     * new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4);
     * */

    /**
     * 协议版本
     * */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    /**
     * 指令
     * */
    @JSONField(serialize = false)
    public abstract Byte getCommand();

    //get 方法
    public Byte getVersion(){
        return version;
    }
    //set方法
    public void setVersion(Byte version){
        this.version = version;
    }
}
