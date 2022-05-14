package cn.sonui.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * 连接工厂创建信道的工具类
 * @author Sonui
 */
public class RabbitMqUtils {
    public static Channel getChannel() throws Exception {
        // 创建连接工厂
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
        return conn.createChannel();
    }
}
