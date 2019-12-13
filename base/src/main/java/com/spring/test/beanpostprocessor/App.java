package com.spring.test.beanpostprocessor;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * @Decription BeanPostProcessor 相关测试
 * @Author yangsj
 * @Date 20191209
 **/
public class App {
    @Test
    public void test(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("org/springframework/bean/bean.xml");
        reader.loadBeanDefinitions(resource);
        BeanPostProcessorTest beanPostProcessorTest = (BeanPostProcessorTest)factory.getBean("beanPostProcessorTest");
        beanPostProcessorTest.disPlay();
    }
    @Test
    public void test2(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("org/springframework/bean/bean.xml");
        reader.loadBeanDefinitions(resource);
        BeanPostProcessorTest beanPostProcessorTest = new BeanPostProcessorTest();
        // 将自定义 BeanPostProcessor 加入到 factory 中
        factory.addBeanPostProcessor(beanPostProcessorTest);
        BeanPostProcessorTest beanPostProcessorTest1 = (BeanPostProcessorTest)factory.getBean("beanPostProcessorTest");
        beanPostProcessorTest1.disPlay();

    }
}
