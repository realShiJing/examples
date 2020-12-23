package com.nchu.apollo.quickstart;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/12/23 上午10:20
 **/
@Component
@ConfigurationProperties(prefix = "order")
public class OrderProperties {
    /**
     * 订单支付超时时长，单位：秒。
     * Apollo 配置中key为 order.pay-timeout-seconds
     */
    private Integer payTimeoutSeconds;

    /**
     * 订单创建频率，单位：秒
     * Apollo 配置中key为 order.create-frequency-seconds
     */
    private Integer createFrequencySeconds;

    public Integer getPayTimeoutSeconds() {
        return payTimeoutSeconds;
    }

    public void setPayTimeoutSeconds(Integer payTimeoutSeconds) {
        this.payTimeoutSeconds = payTimeoutSeconds;
    }

    public Integer getCreateFrequencySeconds() {
        return createFrequencySeconds;
    }

    public void setCreateFrequencySeconds(Integer createFrequencySeconds) {
        this.createFrequencySeconds = createFrequencySeconds;
    }
}
