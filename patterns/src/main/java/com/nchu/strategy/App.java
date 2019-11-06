package com.nchu.strategy;

import org.junit.Test;

/**
 * @Decription 策略模式测试
 * @Author yangsj
 * @Date 20191106 13:53
 **/
public class App {
    @Test
    public void test(){
        //MD5算法加密策略
        Strategy md5Strategy = new MD5Strategy();

        //将具体的策略传入包装类中
        Context context = new Context(md5Strategy);
        context.encrypt();

        //SHA1算法加密策略
        Strategy sha1Strategy = new SHA1Strategy();

        //更改算法策略
        context.setStrategy(sha1Strategy);
        context.encrypt();
    }
}
