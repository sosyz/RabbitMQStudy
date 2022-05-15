package cn.sonui.rabbitmq.work;

import cn.sonui.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author Sonui
 *
 * 消息持久化
 */
public class Task03 {
    /**
     * QUEUE_NAME 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws  Exception{
        Channel chan = RabbitMqUtils.getChannel();
        // 队列持久化
        boolean durable = true;
        chan.queueDeclare(QUEUE_NAME, durable, false, false, null);

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String msg = scanner.nextLine();
            /*
              1 发送到哪个交换机
              2 路由的key值是哪个
              3 其他参数
                    设置生产者发送消息为持久化消息（保存到内存中且要求保存到磁盘）
                    并不是绝对保存不丢失，在写入内存尚未写入到磁盘或者磁盘未保存的时候仍会丢失
              4 发送的消息体的二进制
             */
            chan.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN
                    , msg.getBytes(StandardCharsets.UTF_8));
            System.out.println("[x] Sent '"+msg+"'");
        }
    }
}
