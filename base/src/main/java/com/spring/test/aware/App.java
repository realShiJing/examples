package com.spring.test.aware;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @Decription Aware接口相关测试
 * @Author yangsj
 * @Date 20191209 13:56
 **/
public class App {
    /**
     * Spring 容器在初始化主动检测当前 bean 是否实现了 Aware 接口，
     * 如果实现了则回调其 set 方法将相应的参数设置给该 bean ，这个时候该 bean 就从 Spring 容器中取得相应的资源。
     */
    @Test
    public void test(){
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        ClassPathResource resource = new ClassPathResource("org/springframework/bean/bean.xml");
        reader.loadBeanDefinitions(resource);
        MyApplicationAware applicationAware =(MyApplicationAware)factory.getBean("myApplicationAware");
        applicationAware.disPlay();
    }

    @Test
    public void  test1(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("org/springframework/bean/bean.xml");
        MyApplicationAware myApplicationAware = (MyApplicationAware)applicationContext.getBean("myApplicationAware");
        myApplicationAware.disPlay();

    }



}
