package com.spring.test.initializingbean;

import org.springframework.beans.factory.InitializingBean;

/**
 * @Decription TODO
 * @Author yangsj
 * @Date 20191210 09:27
 **/
public class InitializingBeanTest implements InitializingBean {
    public String name;

    /**
     * 初始化方法，初始化Bean时调用
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBeanTest initializing...");
        this.name = "yangsj 2号";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOtherName(){
        System.out.println("InitializingBeanTest init-method...");
        this.name = "yangsj 3号";
    }
}
