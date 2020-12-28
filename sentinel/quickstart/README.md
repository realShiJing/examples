本案例 分别演示了 Sentinel  API手动配置、注解、整合SpringMVC、整合Apollo数据源动态规则配置

# Sentinel 整合 Apollo 数据源
1. 首先引入  Sentinel 对 Apollo 作为数据源的支持

    ```xml
            <dependency>
                <groupId>com.alibaba.csp</groupId>
                <artifactId>sentinel-datasource-apollo</artifactId>
                <version>1.8.0</version>
            </dependency>
    ```
2. 在配置类中配置ApolloDataSource,并注入到Spring 容器中
    ```java
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
    ```
3. 在Apollo Protol控制台配置限流规则

    ```json
    [
        {
            "resource": "GET:/apollo",
            "limitApp": "default",
            "grade": 1,
            "count": 5,
            "strategy": 0,
            "controlBehavior": 0,
            "clusterMode": false
        }
    ]
    ```
4. Controller 新增 "/apollo" 请求路径处理

    ```java
     @GetMapping("/apollo")
        public String echo() {
            return "Hello Apollo Sentinel";
        }
    ```
   ## 参考
    http://www.iocoder.cn/Spring-Boot/Sentinel/?self  第十一节