package com.spring.test.beanfactorypostprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/**
 * @Decription BeanFactoryPostProcessor 的机制，就相当于给了我们在 Bean 实例化之前最后一次修改 BeanDefinition 的机会，
 * 我们可以利用这个机会对 BeanDefinition 来进行一些额外的操作，比如更改某些 bean 的一些属性，给某些 Bean 增加一些其他的信息等等操作。
 * @Author yangsj
 * @Date 2019/12/11 10:52
 **/
public class BeanFactoryPostProcessor_1 implements BeanFactoryPostProcessor , Ordered {
    /**
     *  该方法工作于 BeanDefinition 加载完成之后，Bean 实例化之前，其主要作用是对加载 BeanDefinition 进行修改
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用 BeanFactoryPostProcessor_1 ...");

        System.out.println("容器中有 BeanDefinition 的个数："+ beanFactory.getBeanDefinitionCount());

        //获取指定的 BeanDefinition
        BeanDefinition bd = beanFactory.getBeanDefinition("testBean");

        MutablePropertyValues pvs = bd.getPropertyValues();
        pvs.addPropertyValue("name","yangsj1");

        pvs.addPropertyValue("age",15);

    }
    /**
     *  排序，定义 BeanFactoryPostProcessor 的执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 2;
    }
}
