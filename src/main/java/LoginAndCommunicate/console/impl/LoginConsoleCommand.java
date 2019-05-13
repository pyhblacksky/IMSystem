package LoginAndCommunicate.console.impl;

import LoginAndCommunicate.console.ConsoleCommand;
import LoginAndCommunicate.packet.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:41
 * @Version: 1.0
 * @Function:
 * @Description:
 *  登录命令控制器
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        System.out.print("输入用户名登录：");
        loginRequestPacket.setUserName(scanner.nextLine());
        loginRequestPacket.setPassword("pwd");//默认密码

        //发送登录数据包
        channel.writeAndFlush(loginRequestPacket);
        waitForLoginResponse();
    }

    //等待登录
    private static void waitForLoginResponse(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


}
