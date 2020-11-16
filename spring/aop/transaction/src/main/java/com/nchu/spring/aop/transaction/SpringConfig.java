package com.nchu.spring.aop.transaction;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Decription 配置启动类
 * @Author yangsj
 * @Date 2020/11/9 4:09 下午
 **/
@Configuration
@ComponentScan({"com.nchu.spring.aop.transaction"})
@Import({DataSourceConfig.class})
@EnableTransactionManagement
public class SpringConfig {

}
