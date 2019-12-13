package com.spring.test.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Decription Spring 管理下的 bean 的生命周期
 * @Author yangsj
 * @Date 2019/12/10 15:10
 **/
public class LifeCycleBean implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, BeanPostProcessor, InitializingBean, DisposableBean {

    LifeCycleBean(){
        System.out.println("构造函数初始化bean...");
    }
    public String name ;

    public void setName(String name) {
        System.out.println("属性注入...");
        this.name = name;
    }

    public void display(){
        System.out.println("方法调用...");
    }
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("注入 BeanClassLoader ...");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("注入 BeanFactory ...");
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("注入 beanName ...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor postProcessAfterInitialization 被调用...");
        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet 被调动...");
    }

    public void initMethod(){
        System.out.println("inti-method 被调用 ...");
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor postProcessAfterInitialization 被调用...");
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean destroy 被调动...");
    }

    public void destroyMethod(){
        System.out.println("destroy-method 被调用...");
    }

}
