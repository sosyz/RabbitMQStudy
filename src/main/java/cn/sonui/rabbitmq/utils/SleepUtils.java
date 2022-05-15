package cn.sonui.rabbitmq.utils;

/**
 * @author sonui
 * sleep类
 */
public class SleepUtils {
    public static void sleep(int second){
        try {
            Thread.sleep(1000L *second);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}
