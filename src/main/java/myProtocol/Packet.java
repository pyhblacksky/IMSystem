package myProtocol;

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
    };
}
