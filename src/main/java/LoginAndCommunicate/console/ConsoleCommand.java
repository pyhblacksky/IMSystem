package LoginAndCommunicate.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @Author: pyh
 * @Date: 2019/5/13 20:03
 * @Version: 1.0
 * @Function:
 * @Description:
 *  控制台命令执行器，对控制台进行重构
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
