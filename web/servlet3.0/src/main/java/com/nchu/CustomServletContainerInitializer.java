package com.nchu;

import com.nchu.filter.HelloWorldFilter;
import com.nchu.servlet.HelloWorldServlet;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * @Decription Servlet 3.0 测试
 *
 * 使用 ServletContainerInitializer 和 SPI 机制，我们的 web 应用便可以彻底摆脱 web.xml 了
 * META-INF/services/javax.servlet.ServletContainerInitializer 配置该接口的实现
 * 指向了 CustomServletContainerInitializer，这样只要在 servlet 3.0 环境下部署，便可以自动加载进行初始化我们定义的Servlet 、Filter
 * @Author yangsj
 * @Date 2019/12/20 11:28
 **/
public class CustomServletContainerInitializer implements ServletContainerInitializer {
    private final static String JAR_HELLO_URL = "/hello";
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        // ServletContext 我们称之为 servlet 上下文，它维护了整个 web 容器中注册的 servlet，filter，listener，
        System.out.println("创建 helloWorldServlet...");
        ServletRegistration.Dynamic servlet = servletContext.addServlet(HelloWorldServlet.class.getSimpleName(), HelloWorldServlet.class);
        servlet.addMapping(JAR_HELLO_URL);

        System.out.println("创建 helloWorldFilter...");
        FilterRegistration.Dynamic filter = servletContext.addFilter(HelloWorldFilter.class.getSimpleName(), HelloWorldFilter.class);

        EnumSet<DispatcherType> dispatcherTypes = EnumSet.allOf(DispatcherType.class);
        dispatcherTypes.add(DispatcherType.REQUEST);
        dispatcherTypes.add(DispatcherType.FORWARD);
        filter.addMappingForUrlPatterns(dispatcherTypes,true,JAR_HELLO_URL);
    }
}
