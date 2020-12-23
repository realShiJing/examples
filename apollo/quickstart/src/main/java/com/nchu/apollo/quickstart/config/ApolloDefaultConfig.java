package com.nchu.apollo.quickstart.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Decription 可以多个 namespace 并指定加载顺序
 * @Author yangsj
 * @Date 2020/12/23 上午11:56
 **/
@Configuration
@EnableApolloConfig(order = 2)
public class ApolloDefaultConfig {
}
