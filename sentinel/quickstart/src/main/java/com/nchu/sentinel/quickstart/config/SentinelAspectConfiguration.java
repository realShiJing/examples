package com.nchu.sentinel.quickstart.config;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.datasource.apollo.ApolloDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/12/28 14:29
 **/
@Configuration
public class SentinelAspectConfiguration {

    /**
     * @Description   配置支持 @SentinelResource
     * @Author yangsj
     * @Date 2020/12/28 下午10:11
     **/
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }

    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * @Description 配置Apollo数据源
     * @Author yangsj
     * @Date 2020/12/28 下午10:09
     **/
    @Bean
    public ApolloDataSource apolloDataSource(ObjectMapper objectMapper) {
        // Apollo 配置。这里先写死，推荐后面写到 application.yaml 配置文件中。
        String appId = applicationName;
        // Apollo Meta 服务地址
        String serverAddress = "http://localhost:8080";
        // Sentinel 限流规则 配置所在命名空间
        String namespace = "application";
        // Sentinel 规则配置key
        String flowRuleKey = "sentinel.flow-rule";

        //创建 ApolloDataSource 对象
        System.setProperty("app.id",appId);
        System.setProperty("apollo.meta",serverAddress);

        ApolloDataSource<List<FlowRule>> apolloDataSource = new ApolloDataSource(namespace, flowRuleKey, "",
                new Converter<String, List<FlowRule>>() {// 转换器，将读取的 Apollo 配置，转换成 FlowRule 数组
            @Override
            public List<FlowRule> convert(String value) {
                try {
                    return Arrays.asList(objectMapper.readValue(value, FlowRule[].class));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        //加载 Sentinel 流控规则
        FlowRuleManager.register2Property(apolloDataSource.getProperty());

        return apolloDataSource;

    }
}
