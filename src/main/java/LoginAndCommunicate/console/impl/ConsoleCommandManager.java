package LoginAndCommunicate.console.impl;

import LoginAndCommunicate.console.ConsoleCommand;
import LoginAndCommunicate.util.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:04
 * @Version: 1.0
 * @Function:
 * @Description:
 *  管理控制台命令执行器
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<String, ConsoleCommand>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("joinGroup", new JoinGroupConsoleCommand());
        consoleCommandMap.put("listGroupMembers", new ListGroupMembersConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入指令，目前指令有 ： ");
        System.out.println("'sendToUser' :  发送消息给某个用户，需要使用userId，并用空格隔开");
        System.out.println("'logout' : 用户登出");
        System.out.println("'createGroup': 创建群组，并拉人进群");
        System.out.println("'joinGroup': 加入群聊");
        System.out.println("'listGroupMembers': 获取群中的成员列表");
        //获取第一个指令
        String command = scanner.next();

        if (!SessionUtil.hasLogin(channel)) {
            System.out.println("未登录");
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand != null){
            consoleCommand.exec(scanner, channel);
        } else{
            System.err.println("无法识别["+command+"]指令， 指令不正确， 请重新输入");
        }
    }
}
