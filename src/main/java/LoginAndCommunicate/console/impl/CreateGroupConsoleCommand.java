package LoginAndCommunicate.console.impl;

import LoginAndCommunicate.console.ConsoleCommand;
import LoginAndCommunicate.packet.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:06
 * @Version: 1.0
 * @Function:
 * @Description:
 *  创建群命令控制器
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】 输入userId列表，userId之间使用英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
