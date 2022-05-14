package cn.sonui.rabbitmq.work;

import cn.sonui.rabbitmq.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author Sonui
 * 这是一个工作线程 相当于一个消费者
 */
public class Work01 {
    /**
     * QUEUE_NAME 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    /**
     * 已设置为允许多个实例运行
     * @param args 运行参数
     * @throws Exception 错误
     */
    public static void main(String[] args) throws Exception {
        Channel chan = RabbitMqUtils.getChannel();

        // 声明回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> System.out.println("Work2收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8));

        CancelCallback cancelCallback = consumerTag -> System.out.println("Work2消息消费被中断");

        // 消息的接收
        chan.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
        System.out.println("Work2等待接收消息");
    }
}
