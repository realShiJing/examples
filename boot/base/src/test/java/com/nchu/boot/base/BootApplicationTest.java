package com.nchu.boot.base;

import com.nchu.boot.starter.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/11/17 下午2:51
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class BootApplicationTest {
    /**
     * @Description   测试自定义starter
     * @Author yangsj
     * @Date 2020/11/17 下午2:57
     **/
   /* @Autowired
    public User user;*/

    @Autowired
    public ApplicationContext applicationContext;

    @Test
    public void testStarter(){
        boolean hasUser = applicationContext.containsBean("user");
        System.out.println("自定义配置类是否生效:"+hasUser);

        /* System.out.println(this.user);*/
    }

}
