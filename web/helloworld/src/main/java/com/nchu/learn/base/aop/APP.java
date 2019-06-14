package com.nchu.learn.base.aop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//为什么注解的方式不能注入
/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")*/
public class APP {
    @Autowired
    private Target target;

    @Test
    public void aspecctTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Target target = (Target) applicationContext.getBean("target");
        target.test("aspect");
    }
}