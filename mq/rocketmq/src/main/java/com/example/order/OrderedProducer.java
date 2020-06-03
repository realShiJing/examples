package com.example.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/11 14:03
 **/
public class OrderedProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("example_order_group");
        // 注册到 Namesrv
        producer.setNamesrvAddr("localhost:9876");
        // 要发送的那条消息

        producer.start();
        // 四个 queue 轮流发送
        for (int j = 0; j < 4; j++) {
            // 连续发20条消息到同一个queue中
            for (int i = 0; i < 20; i++) {
                Message message = new Message("orderTopic",("hello "+i).getBytes());
                producer.send(message,
                        // queue 选择器 ，向 topic 中的哪个 queue 去写消息
                        (list,// 当前 topic 里面包含的所有 queue
                         msg,// 具体要发的那条消息
                         o// 对应到 send（） 里的 args
                        ) -> {
                            // 手动选择一个 queue
                            MessageQueue queue = list.get(Integer.valueOf((Integer) o));
                            // 返回过滤出来的 MessageQueue ,消息会发送到这个队列中
                            return queue;
                        },j,2000);
            }
        }


        //producer.shutdown();
    }
}