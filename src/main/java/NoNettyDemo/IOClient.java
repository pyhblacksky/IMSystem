package NoNettyDemo;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: pyh
 * @Date: 2019/5/11 14:15
 * @Version: 1.0
 * @Function:
 * @Description:
 *  客户端
 */
public class IOClient {

    public static void main(String[] args){
        new Thread(new Runnable() {
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 8000);

                    while (true){
                        try {
                            socket.getOutputStream().write((new Date() + ": hello world!").getBytes());
                            Thread.sleep(2000);//2秒发送一次
                        } catch (Exception e){
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
