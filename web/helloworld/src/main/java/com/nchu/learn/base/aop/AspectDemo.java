package com.nchu.learn.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面
 */
@Aspect
@Component
public class AspectDemo {

    /**
     * @Description 切点
     * @Author yangsj
     * @Date 2019/6/13 13:55
     * @Param [s]
     * @return void
     **/
    @Pointcut(value = "execution(* *.test(..)) && args(s)")
    public void pc(String s){
        System.out.println("切点执行");
    }

    /**
     * 通知方法
     */
    @Before("pc(s)")
    public void beforeTest(JoinPoint jp, String s){
        System.out.println("前置起点通知------>arg："+s);
    }
    /**
     * 通知方法
     */
    @After("pc(s)")
    public void afterTest(String s){
        System.out.println("后置终点通知------>arg："+s);
    }
    /**
     * 通知方法
     */
    @AfterReturning(pointcut = "pc(s)", returning = "i")
    public void afterReturningTest(Object i,String s){
        System.out.println("后置返回通知------>return："+i+",arg："+s);
    }
    /**
     * 通知方法
     */
    @AfterThrowing(pointcut = "pc(s)",throwing = "e")
    public void afterThrowingTest(Exception e,String s){
        System.out.println("后置异常通知------>"+e.getMessage()+"，arg："+s);
    }
    /**
     * 通知方法
     */
    @Around("pc(s)")
    public Object aroundTest(ProceedingJoinPoint jp, String s) throws Throwable {
        System.out.println("环绕前置通知------>arg："+s);
        //原方法参数
        Object[] os = jp.getArgs();
        //原方法返回值
        Object proceed = jp.proceed();
        s = "update";
        os[0] = s;
        try {
            jp.proceed(os);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("环绕后置通知------>arg："+s);
        return proceed;
    }
}