package com.example.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/11 14:05
 **/
public class OrderedConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("example_order_group");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.subscribe("orderTopic","*");

        consumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            for (MessageExt msg : msgs) {
                System.out.println(new String(msg.getBody())+"Thread:"+Thread.currentThread().getName()+"queueId"+msg.getQueueId());
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });

        consumer.start();

        System.out.printf("Consumer Started.%n");
    }
}