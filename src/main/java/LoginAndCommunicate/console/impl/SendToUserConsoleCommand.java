package LoginAndCommunicate.console.impl;

import LoginAndCommunicate.console.ConsoleCommand;
import LoginAndCommunicate.packet.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:45
 * @Version: 1.0
 * @Function:
 * @Description:
 *  发送消息给用户的命令控制器
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("发送消息给某个用户：");

        String toUserId = scanner.next();
        String message = scanner.next();
        channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
    }
}
