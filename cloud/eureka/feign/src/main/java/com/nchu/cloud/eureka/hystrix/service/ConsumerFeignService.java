package com.nchu.cloud.eureka.hystrix.service;

import com.nchu.cloud.vo.ResultVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-EUREKA-PROVIDER")
public interface ConsumerFeignService {

    @GetMapping("/provider/getInfo/{id}")
    ResultVo getInfo(@PathVariable("id") String id);
}
