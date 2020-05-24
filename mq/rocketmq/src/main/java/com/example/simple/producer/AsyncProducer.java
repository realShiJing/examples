package com.example.simple.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;

/**
 * @Decription
 * Asynchronous transmission is generally used in response time sensitive business scenarios
 * @Author yangsj
 * @Date 2020/5/1 11:35 下午
 **/
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new DefaultMQProducer("async_producer_group");
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876");
        //Launch the instance.
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(1);

        //Create a message instance, specifying topic, tag and message body.
        Message msg = new Message("myTopic",
                "TagA",
                "Hello world ".getBytes(RemotingHelper.DEFAULT_CHARSET));
        producer.send(msg, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.printf("%-10d OK %s %n",
                        sendResult.getMsgId());
                producer.shutdown();
            }
            @Override
            public void onException(Throwable e) {
                System.out.printf("%-10d Exception %s %n", e);
                e.printStackTrace();
                producer.shutdown();
            }
        });
    }
}