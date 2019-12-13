package com.spring.test.propertyoverrideconfigurer;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription 测试 PropertyOverrideConfigurer
 * PropertyOverrideConfigurer 允许我们对 Spring 容器中 bean 定义的 property 信息进行覆盖替换。
 *
 * PropertyOverrideConfigurer 的使用规则是 beanName.propertyName=value
 * 这里需要注意的是 beanName.propertyName 则是该 bean 中存在的属性。
 * @Author yangsj
 * @Date 2019/12/11 11:20
 **/
public class App {
    /**
     * PropertyOverrideConfigurer 定义的文件取代了 bean 中默认的值
     */
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("/org/springframework/bean/application.xml");
        ConfigBean configBean = (ConfigBean) context.getBean("configBean");
        System.out.println("configBean name "+ configBean.getName());
    }
}
