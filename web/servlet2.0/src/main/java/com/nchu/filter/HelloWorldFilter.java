package com.nchu.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Decription servlet3.0 之前版本 测试
 * @Author yangsj
 * @Date 2019/12/20 10:23
 **/
public class HelloWorldFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("触发 hello world 过滤器...");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
