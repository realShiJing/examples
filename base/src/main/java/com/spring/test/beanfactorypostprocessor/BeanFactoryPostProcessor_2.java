package com.spring.test.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @Decription
 * 在 Spring 容器启动阶段，Spring 也提供了一种容器扩展机制：BeanFactoryPostProcessor，
 * 该机制作用于容器启动阶段，允许我们在容器实例化 Bean 之前对注册到该容器的 BeanDefinition 做出修改。
 *
 * 与 BeanPostProcessor 一样，BeanFactoryPostProcessor 同样支持排序，
 * 一个容器可以同时拥有多个 BeanFactoryPostProcessor ，这个时候如果我们比较在乎他们的顺序的话，可以实现 Ordered 接口。
 * @Author yangsj
 * @Date 2019/12/11 10:52
 **/
public class BeanFactoryPostProcessor_2 implements BeanFactoryPostProcessor , Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用 BeanFactoryPostProcessor_2 ...");

        System.out.println("容器中有 BeanDefinition 的个数："+ beanFactory.getBeanDefinitionCount());

        //获取指定的 BeanDefinition
        BeanDefinition bd = beanFactory.getBeanDefinition("testBean");

        MutablePropertyValues pvs = bd.getPropertyValues();

        pvs.addPropertyValue("name","yangsj2");

    }

    /**
     *  排序，定义 BeanFactoryPostProcessor 的执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
