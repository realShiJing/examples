package com.nchu.spring.ioc.anno;

import org.springframework.context.annotation.*;

/**
 * @Decription 配置启动类
 * @Author yangsj
 * @Date 2020/11/9 4:09 下午
 **/
@Configuration
@ComponentScan({"com.nchu.spring.ioc.anno"})
@Import({DataSourceConfig.class})
public class SpringConfig {

}
