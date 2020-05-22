package com.nchu.cloudalibaba.sentinel.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/22 13:58
 **/
@RestController
public class CircleBreakerController {

    public static final String SERVICE_URL = "http://nacos-provider";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/provider/getInfo/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    public String fallback(@PathVariable Long id)
    {
        String result = restTemplate.getForObject(SERVICE_URL + "/provider/getInfo/"+id,String.class);

        if (id == 4) {
            throw new IllegalArgumentException ("IllegalArgumentException,非法参数异常....");
        }else if (result == null) {
            throw new NullPointerException ("NullPointerException,该ID没有对应记录,空指针异常");
        }

        return result;
    }


    /**
     * @Description 本例是fallback
     * @Author yangsj
     * @Date 2020/5/22 14:08
     **/
    public String handlerFallback(@PathVariable  Long id,Throwable e) {
        return "兜底异常handlerFallback,exception内容  "+e.getMessage();
    }

    /**
     * @Description 本例是blockHandler
     * @Author yangsj
     * @Date 2020/5/22 14:08
     **/
    public String blockHandler(@PathVariable  Long id, BlockException blockException) {
        return "blockHandler-sentinel限流,无此流水: blockException  "+blockException.getMessage();
    }
}
