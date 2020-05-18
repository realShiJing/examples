package com.nchu.cloud.eureka.stream.controller;

import com.nchu.cloud.eureka.stream.service.MessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Description TODO 
 * @Author yangsj
 * @Date 2020/5/18 19:03
 **/
@RestController
public class MessageController
{
    @Resource
    private MessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage()
    {
        return messageProvider.send();
    }

}
