# 项目启动必要配置
## Apollo 配置
AppId = apollo-quickstart

## namespace
 ### application

```properties
sms.enable = false
order.payTimeoutSeconds = 100
order.createFrequencySeconds = 20
order.create-frequency-seconds = 200
order.pay-timeout-seconds = 30
```

### namespring-rocketmq
```properties
rocketmq.name-server = 127.0.0.1:9876
rocketmq.producer.group = PID_ACCOUNT
sms.enable = true
order.pay-timeout-seconds = 10000
order.create-frequency-seconds = 20000
server.servlet.context-path = test

```

### organization5.spring-boot-http
注意该配置为继承公共命名空间配置，organization5为所属机构
```properties
server.servlet.context-path = /quickstart
```

# 参考
极简入门 http://www.iocoder.cn/Apollo/install/
SpringBoot集成 http://www.iocoder.cn/Spring-Boot/config-apollo/?github

# 官方文档
快速开始 https://github.com/ctripcorp/apollo/wiki/Quick-Start