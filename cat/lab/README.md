# 前提
1. 该项目启动路径的根目录 需配置/data/appdatas/cat/client.xml文件，文件中配置CAT服务地址
# 调用链
ui
   - office
        - account
        - customer
# 原理
1. 该模块通过自定义过滤器 Filter 来埋点MVC调用
2. 自定义 ClientHttpRequestInterceptor 来埋点远程调用
3. 在发起远程调用时在 HttpHeaders 中保存和传递CAT调用链上下文
4. 在接受远程调用服务提供方，从 HttpHeaders 中恢复远程调用的CAT调用链上下文
5. CAT调用链上下文主要保存 该次调用的根节点、父节点及子节点