package com.nchu.cloud.eureka.controller;

import com.nchu.cloud.eureka.service.ConsumerFeignService;
import com.nchu.cloud.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/5/8 15:44
 **/
@RestController
public class ConsumerFeignController {

    @Autowired
    ConsumerFeignService consumerFeignService;

    @GetMapping("/consumer/provider/getInfo/{id}")
    public  ResultVo getInfo(@PathVariable("id") String id){
      return consumerFeignService.getInfo(id);
    }

}
