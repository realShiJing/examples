package com.nchu.sentinel.quickstart.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Decription Sentinel 整合 Spring MVC
 * @Author yangsj
 * @Date 2020/12/28 14:41
 **/
@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SentinelWebMvcConfig sentinelWebMvcConfig = new SentinelWebMvcConfig();
        //指定请求方法:GET、POST等等
        sentinelWebMvcConfig.setHttpMethodSpecify(true);
        //默认使用统一Web上下文 如果希望支持链路关系的流控策略则应该设置为false
        sentinelWebMvcConfig.setWebContextUnify(true);
        // 用来标识来源 可针对性的对特定客户端的请求进行流控
        // 设置请求来源解析器。用于黑白名单控制功能。
        sentinelWebMvcConfig.setOriginParser(new RequestOriginParser() { // 设置请求来源解析器。用于黑白名单控制功能。
        //黑白名单配置
        @Override
        public String parseOrigin(HttpServletRequest request) {
            // 从 Header 中，获得请求来源
            // 如果 Sentinel 控制台配置了授权规则  白名单 流控应用：test
            // 那么请求头中必须含有参数s-user = test 才允许通过
            String origin = request.getHeader("s-user");
            // 如果为空，给一个默认的
            // 我们判断未获得请求来源的时候，设置默认为 default。
            // 原因是，Sentinel 提供的 AuthorityRuleChecker 在进行黑白名单控制时，如果请求来源为空，直接就通过了
            if (StringUtils.isEmpty(origin)) {
                origin = "default";
            }
            return origin;
        }

    });

        /// 添加 SentinelWebInterceptor 拦截器
        registry.addInterceptor(new SentinelWebInterceptor(sentinelWebMvcConfig)).addPathPatterns("/**");
    }
}
