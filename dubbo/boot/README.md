## Dubbo整合CAT

###前提

将项目 https://github.com/dianping/cat/tree/master/integration/dubbo install 安装到本地仓库

### 步骤
1. 将安装到本地maven仓库的cat-monitor插件引入Dubbo 服务提供方和服务消费方项目
    ```xml
    <dependency>
        <groupId>net.dubboclub</groupId>
        <artifactId>cat-monitor</artifactId>
        <version>0.0.6</version>
    </dependency>
    ```

2. 在 Dubbo 服务提供方和服务消费方项目 META-INF/app.properties 添加app.name