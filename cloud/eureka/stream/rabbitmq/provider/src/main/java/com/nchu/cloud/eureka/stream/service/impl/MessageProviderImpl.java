package com.nchu.cloud.eureka.stream.service.impl;

import com.nchu.cloud.eureka.stream.service.MessageProvider;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;


/**
 * @Description 消息发送
 * @Author yangsj
 * @Date 2020/5/18 19:00
 **/
@EnableBinding(Source.class) //定义消息的推送管道
public class MessageProviderImpl implements MessageProvider
{
    @Resource
    private MessageChannel output; // 消息发送管道

    @Override
    public String send()
    {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial: "+serial);
        return null;
    }
}
