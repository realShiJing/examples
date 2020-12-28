# 测试Apollo多环境配置
此案例中测试两个环境开发环境dev、生产环境pro

#### 每个环境上都要单独部署一套 apollo-adminservice 和 apollo-configservice
1. 搭建生产环境  ApolloConfigProDB 数据库，导入https://github.com/ctripcorp/apollo/blob/master/scripts/sql/apolloconfigdb.sql脚本
2. 修改表ServerConfig 的 eureka.service.url 属性为生产环境的meta地址
3. conf/app.properties 修改 appid 保证唯一
4. conf/application-github.properties 修改连接数据库为 ApolloConfigProDB
5. 启动脚本修改 SERVER_PORT 服务端口 及 LOG_DIR日志目录

#### 修改 apollo-portal 支持多环境
1. 修改表 ServerConfig 的 apollo.portal.envs属性为dev,pro
2. 修改 apollo-portal 应用下 conf/apollo-env.properties 的 pro.meta=http://127.0.0.1:8081

#### 依次启动 portal、开发环境adminservice、configservice及生产环境adminservice、configservice

#### 启动脚本
```shell script
#!/bin/bash

echo "启动apollo的protal服务..."
/usr/local/home/apollo-portal/scripts/startup.sh 

echo "启动开发环境apollo服务..."
/usr/local/home/apollo-configservice/scripts/startup.sh 
/usr/local/home/apollo-adminservice/scripts/startup.sh 

echo "启动PRO环境apollo服务..."
/usr/local/home/apollo-configservice-pro/scripts/startup.sh 
/usr/local/home/apollo-adminservice-pro/scripts/startup.sh 

```

####关闭脚本
```shell script
#!/bin/bash
echo "关闭apollo所有环境..."
/usr/local/home/apollo-portal/scripts/shutdown.sh
/usr/local/home/apollo-configservice/scripts/shutdown.sh
/usr/local/home/apollo-adminservice/scripts/shutdown.sh
/usr/local/home/apollo-configservice-pro/scripts/shutdown.sh
/usr/local/home/apollo-adminservice-pro/scripts/shutdown.sh
```

#### 检查服务是否正常

1. 访问http://127.0.0.1:8080/ 查看开发环境 config服务和admin服务是否正常在线
2. 访问http://127.0.0.1:8081/ 查看生产环境 config服务和admin服务是否正常在线

#### Apollo配置中心添加配置
1. 创建项目AppId = demo-application-profiles
2. 选中DEV,在applicaion默认命名空间下添加 server.port= 8089 的配置
3. 选中PRO,在applicaion默认命名空间下添加 server.port= 8088 的配置

#### 测试配置多环境配置参数是否生效

1. 在 Program arguments 添加启动参数
2. 开发环境--spring.profiles.active=dev
3. 生产环境--spring.profiles.active=prod

#### 测试结果
- 开发环境控制台会输出
```text
Tomcat started on port(s): 8089 (http) with context path ''
```

- 生产环境控制台会输出
```text
Tomcat started on port(s): 8088 (http) with context path ''
```