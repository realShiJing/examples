package com.nchu.cloud.eureka.hystrix.service.fallback;

import com.nchu.cloud.eureka.hystrix.service.ConsumerService;
import org.springframework.stereotype.Component;

/**
 * @Decription 服务降级处理 service,需实现远程调用 service
 * @Author yangsj
 * @Date 2020/5/14 14:28
 **/
@Component
public class ConsumerFallBackService implements ConsumerService {
    @Override
    public String getInfo(Integer id) {
        return "----ConsumerFallBackService fall back-getInfo";
    }

    @Override
    public String getTimeOut(Integer id) {
        return "----ConsumerFallBackService fall back-getTimeOut";
    }

    @Override
    public String circuitBreaker(Integer id) {
        return "----ConsumerFallBackService fall back-circuitBreaker";
    }
}
