package cn.sonui.rabbitmq.work;

import cn.sonui.rabbitmq.utils.RabbitMqUtils;
import cn.sonui.rabbitmq.utils.SleepUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @author Sonui
 * 模拟快处理 1s
 */
public class Work02 {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        Channel chan = RabbitMqUtils.getChannel();
        System.out.println("Work01 start");

        // 声明回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            SleepUtils.sleep(1);//sleep 3s
            System.out.println("Work01收到消息：" + new String(delivery.getBody(), StandardCharsets.UTF_8));
            //进行手动应答
            /*
             1 消息标记 tag
             2 是否批量应答
             */
            chan.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        CancelCallback cancelCallback = consumerTag -> System.out.println("Work01消息消费被中断");

        // 设置不公平分发
        chan.basicQos(1);
        //采用手动应答
        chan.basicConsume(QUEUE_NAME, false, deliverCallback,cancelCallback);
    }
}
