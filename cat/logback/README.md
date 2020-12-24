1. 在logback-spring.xml配置文件中配置 com.dianping.cat.logback.CatLogbackAppender
2. CatLogbackAppender源码中定义 CAT 只能对日志级别大于 Error 的日志埋点，上送CAT服务器