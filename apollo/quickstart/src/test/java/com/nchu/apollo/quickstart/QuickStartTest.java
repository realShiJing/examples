package com.nchu.apollo.quickstart;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Decription Apollo 使用API
 * @Author yangsj
 * @Date 2020/12/23 上午10:55
 **/
public class QuickStartTest {
    // 配置启动参数  -Dapp.id=apollo-quickstart -Denv=DEV -Ddev_meta=http://localhost:8080
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(QuickStartTest.class);
        //读取默认namespace下的配置信息
        Config appConfig = ConfigService.getAppConfig();
        //获取配置信息,第一个参数：配置的key，第二个参数：默认值
        String property = appConfig.getProperty("sms.enable", null);

        logger.info("sms.enable:{}",property);

        //读取指定 namespace下的配置信息
        Config config = ConfigService.getConfig("organization5.spring-boot-http");
        String contextPath = config.getProperty("server.servlet.context-path", null);
        logger.info("context-path:{}",contextPath);



    }
}
