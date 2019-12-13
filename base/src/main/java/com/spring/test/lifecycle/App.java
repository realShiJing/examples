package com.spring.test.lifecycle;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @Decription Spring 管理下的 bean 的生命周期
 * @Author yangsj
 * @Date 20191209
 **/
public class App {
    @Test
    public void test(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 设置 BeanPostProcessor
        factory.addBeanPostProcessor(new LifeCycleBean());
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("org/springframework/bean/bean.xml");
        reader.loadBeanDefinitions(resource);
        LifeCycleBean lifeCycleBean = (LifeCycleBean)factory.getBean("lifeCycleBean");
        lifeCycleBean.display();
        // 关闭容器，销毁 bean ,销毁前调用destroy方法 和自定义destroy-method
        factory.destroySingletons();
    }
}
