package cn.sonui.rabbitmq.work;

import cn.sonui.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Sonui
 *
 * 手动确认消息
 */
public class Task02 {
    /**
     * QUEUE_NAME 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws  Exception{
        Channel chan = RabbitMqUtils.getChannel();
        chan.queueDeclare(QUEUE_NAME, false, false, false, null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            /*
              1 发送到哪个交换机
              2 路由的key值是哪个
              3 其他参数
              4 发送的消息体的二进制
             */
            chan.basicPublish("", QUEUE_NAME, null, msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent '"+msg+"'");
        }
    }
}
