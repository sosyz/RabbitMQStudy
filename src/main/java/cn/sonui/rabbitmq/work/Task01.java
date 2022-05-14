package cn.sonui.rabbitmq.work;

import cn.sonui.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;

import java.util.Scanner;

/**
 * @author Sonui
 * 生产者
 */
public class Task01 {
    /**
     * QUEUE_NAME 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Channel chan = RabbitMqUtils.getChannel();

        /* 声明队列
          1 队列名称
          2 是否持久化到硬盘
          3 是否独占队列 即只允许一个消费者消费队列 false只允许一个消费者消费队列
          4 是否自动删除队列 最后一个消费者断开连接后是否删除 false不自动删除队列
          5 队列的其他参数
         */
        chan.queueDeclare(QUEUE_NAME, false, false, false, null);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            /*
              1 发送到哪个交换机
              2 路由的key值是哪个
              3 其他参数
              4 发送的消息体的二进制
             */
            chan.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            System.out.println("[x] Sent '"+msg+"'");
        }
    }
}
