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
        Message message = new Message("orderTopic","hello RocketMq".getBytes());

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
        },0,2000);
        producer.start();
        //producer.shutdown();
    }
}