package cn.sonui.rabbitmq.work;

import cn.sonui.rabbitmq.utils.RabbitMqUtils;
import cn.sonui.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author Sonui
 * 模拟慢处理 10s
 */
public class Work03 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel chan = RabbitMqUtils.getChannel();
        System.out.println("Work02 start");

        // 声明回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            SleepUtils.sleep(10);//sleep 10s
            System.out.println("Work02收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8));
            //进行手动应答
            /*
             1 消息标记 tag
             2 是否批量应答
             */
            chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = consumerTag -> System.out.println("Work02消息消费被中断");

        //采用手动应答
        chan.basicConsume(QUEUE_NAME, false, deliverCallback,cancelCallback);
    }
}
