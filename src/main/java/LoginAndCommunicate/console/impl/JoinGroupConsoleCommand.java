package LoginAndCommunicate.console.impl;

import LoginAndCommunicate.console.ConsoleCommand;
import LoginAndCommunicate.packet.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: pyh
 * @Date: 2019/5/14 8:22
 * @Version: 1.0
 * @Function:
 * @Description:
 *  控制台添加群加入命令处理器
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

        System.out.print("输入 groupId, 加入群聊：");
        String groupId = scanner.next();

        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
