package cn.sonui.rabbitmq.one;

import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;

public class Consumer {
    // 队列的名称
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        // 创建一个输入流

        // 创建一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置服务地址
        factory.setHost("[::1]");
        // 端口
        factory.setPort(5672);
        // 设置账号信息，用户名、密码、vhost
        factory.setVirtualHost("/test");
        factory.setUsername("test");
        factory.setPassword("123456");
        // 通过工程获取连接
        Connection conn = factory.newConnection();
        Channel chan = conn.createChannel();

        // 声明回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> System.out.println("消费者1收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8));

        CancelCallback cancelCallback = consumerTag -> System.out.println("消息消费被中断");
        /**
         * 消费者消费消息
         * 1 队列名称
         * 2 是否自动ACK应答
         * 3 消费者未成功消费的回调
         * 4 消费者取消消费的回调
         */

        chan.basicConsume(QUEUE_NAME, true, deliverCallback, cancelCallback);
    }
}
