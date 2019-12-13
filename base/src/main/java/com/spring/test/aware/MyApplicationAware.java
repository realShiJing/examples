package com.spring.test.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Decription  自定义 Aware ,用于调试
 * @Author yangsj
 * @Date 20191209 11:26
 **/
public class MyApplicationAware implements BeanNameAware, BeanFactoryAware, BeanClassLoaderAware, ApplicationContextAware {
    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private ApplicationContext applicationContext;
    @Override
    public void setBeanName(String beanName) {
        System.out.println("调用了 BeanNameAware 的 setBeanName 方法");
        this.beanName = beanName;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("调用了 BeanClassLoader 的 setBeanClassLoader 方法");
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("调用了 BeanFactory 的 setBeanFactory 方法");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("调用了 ApplicationContext 的 setApplicationContext 方法");
        this.applicationContext = applicationContext;
    }

    public void disPlay(){
        System.out.println("beanName：" + beanName);
        System.out.println("是否为单例：" + beanFactory.isSingleton(beanName));
        if (applicationContext != null)
            System.out.println("系统环境：" + applicationContext.getEnvironment());
    }
}
