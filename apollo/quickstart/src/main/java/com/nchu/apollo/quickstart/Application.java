package com.nchu.apollo.quickstart;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/12/23 上午10:19
 **/
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * @Description  测试注解 @ConfigurationProperties 配置属性自动注入
     * @Author yangsj
     * @Date 2020/12/23 上午10:42
     **/
    @Component
    public class OrderPropertiesCommandLineRunner implements CommandLineRunner{
        private final Logger logger = LoggerFactory.getLogger(getClass());

        @Autowired
        private OrderProperties orderProperties;
        @Override
        public void run(String... args) throws Exception {
            logger.info("payTimeoutSeconds:" + orderProperties.getPayTimeoutSeconds());
            logger.info("createFrequencySeconds:" + orderProperties.getCreateFrequencySeconds());

        }
    }

    /**
     * @Description  测试 @Value 注解属性注入
     * 自动注入时
     * order.create-frequency-seconds 会覆盖掉 order.createFrequencySeconds
     * @Author yangsj
     * @Date 2020/12/23 上午10:42
     **/
    @Component
    public class ValuePropertiesCommandLineRunner implements CommandLineRunner{
        private final Logger logger = LoggerFactory.getLogger(getClass());
//        @Value(value = "${order.pay-timeout-seconds}")
        @Value(value = "${order.payTimeoutSeconds}")
        private String payTimeoutSeconds;

//        @Value(value = "${order.create-frequency-seconds}")
        @Value(value = "${order.createFrequencySeconds}")
        private String createFrequencySeconds;

        //配置注入多个namespace命名空间的配置信息
        @Value(value = "${rocketmq.name-server}")
        private String nameServer;

        @Value(value = "${server.servlet.context-path}")
        private String contextPath;

        @Override
        public void run(String... args) throws Exception {
            logger.info("@Value 注解注入 payTimeoutSeconds:" + payTimeoutSeconds);
            logger.info("@Value 注解注入 createFrequencySeconds:" + createFrequencySeconds);
            logger.info("@Value 注解注入 rocketmq.name-server:" + nameServer);
            logger.info("@Value 注解注入 server.servlet.context-path:" + contextPath);
        }
    }

    /**
     * @Description 注入指定配置空间的配置
     * @Author yangsj
     * @Date 2020/12/23 下午2:10
     **/
    @Component
    public class AnnotaionApolloConfigCommandLineRunner implements CommandLineRunner{
        private final Logger logger = LoggerFactory.getLogger(getClass());
        //注入命名空间 spring-rocketmq 的配置
        @ApolloConfig("spring-rocketmq")
        private Config apolloConfig;
        @Override
        public void run(String... args) throws Exception {
            logger.info("@ApolloConfig 注解注入 命名空间spring-rocketmq的配置信息:" + apolloConfig.getPropertyNames());
        }
    }
}
