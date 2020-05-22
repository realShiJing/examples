package com.nchu.cloudalibaba.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nchu.cloudalibaba.sentinel.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/22 9:55
 **/
@RestController
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        return "--------testA";
    }

    @GetMapping("/testB")
    public String testB(){
        return "--------testB";
    }

    @GetMapping("/testHotKey")
    @SentinelResource(value = "testHotKey",blockHandler = "testHotKeyHandle")
    public String testHotKey(@RequestParam(value = "p1",required = false) String p1,
                             @RequestParam(value = "p2",required = false) String p2){
        return "--------testHotKey";
    }

    public String testHotKeyHandle (String p1, String p2, BlockException bl){
        return "--------blockHandler";
    }


    @GetMapping("/byResource")
    @SentinelResource(value = "byResource",blockHandler = "handleException")
    public String byResource()
    {
        return "------------byResource";
    }
    public String handleException(BlockException exception)
    {
        return "------------服务不可用";
    }

    @GetMapping("/byUrl")
    @SentinelResource(value = "byUrl",blockHandler = "handleException")
    public String byUrl()
    {
        return "------------byUrl";
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler",blockHandlerClass = CustomerBlockHandler.class,
    blockHandler = "handlerException")
    public String customerBlockHandler(){
        return "------------ customerBlockHandler";
    }
}
