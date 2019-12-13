package com.spring.test.beanfactorypostprocessor;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Decription 测试 BeanFactoryPostProcessor
 * @Author yangsj
 * @Date 2019/12/11 10:59
 **/
public class App {
    @Test
    public void test(){
        // 在该测试方法中，我们使用的是 ApplicationContext ，对于 ApplicationContext 来说，使用 BeanFactoryPostProcessor 非常方便，
        // 因为他会自动识别配置文件中的 BeanFactoryPostProcessor 并且完成注册和调用，我们只需要简单的配置声明即可。
        // 而对于 BeanFactory 容器来说则不行，他和 BeanPostProcessor 一样需要容器主动去进行注册调用，方法如下：
       /* BeanFactoryPostProcessor_1 beanFactoryPostProcessor1 = new BeanFactoryPostProcessor_1();
        beanFactoryPostProcessor1.postProcessBeanFactory(factory);*/
        ApplicationContext context = new ClassPathXmlApplicationContext("/org/springframework/bean/beanfactorypostprocessor.xml");
        TestBean testBean = (TestBean) context.getBean("testBean");
        System.out.println("testBean name "+ testBean.getName());
        System.out.println("testBean name "+ testBean.getAge());
    }
}
