package com.example.dubbo.boot.config;

import com.alibaba.dubbo.config.*;
import com.nchu.dubbo.DemoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/5/5 3:48 下午
 **/
@Configuration
public class MyDubboConfig {

    @Bean
   public ApplicationConfig applicationConfig(){
       ApplicationConfig applicationConfig = new ApplicationConfig();
       applicationConfig.setName("hello-world-app-boot");
       return applicationConfig;
   }

   @Bean
   public RegistryConfig registryConfig(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("127.0.0.1:2181");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
   }

   @Bean
   public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        return protocolConfig;
   }

  /* @Bean
   public ServiceConfig<DemoService> demoServiceConfig(DemoService demoService){
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(demoService);
        serviceConfig.setVersion("1.0.0");
        //配置每一个method的信息
        MethodConfig methodConfig = new MethodConfig();
        methodConfig.setName("sayHello");
        //设置方法的超时时间
        methodConfig.setTimeout(6000);
        //将method的设置关联到service配置中
        List<MethodConfig> methodConfigs = new ArrayList<>();
        methodConfigs.add(methodConfig);
        serviceConfig.setMethods(methodConfigs);

        return serviceConfig;
   }*/
}
