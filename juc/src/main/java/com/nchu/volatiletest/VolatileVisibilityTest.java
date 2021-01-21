package com.nchu.volatiletest;

/**
 * @ClassName: volatile 可见性测试
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/21 10:11
 **/
public class VolatileVisibilityTest {
    private static /*volatile*/ boolean initFlag = false;

    /**
      *@return:  开启两个线程，第一个线程死循环 ,第二个线程修改共享变量的值
      *@Description: main
      *@Param: [args]
      *@Author: yangsj
      *@Date: 10:29 2021/1/21
      *@Modify: yangsj
      **/
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("waiting data");
            while (!initFlag){

            }
            System.out.println("=============success");
        }).start();

        Thread.sleep(2000);

        new Thread(()->{prepareData();}).start();
    }

    public static void prepareData(){
        System.out.println("prepare data ...");
        initFlag = true;
        System.out.println("prepare data end...");
    }
}
