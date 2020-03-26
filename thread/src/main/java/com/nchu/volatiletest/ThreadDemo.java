package com.nchu.volatiletest;

/**
 * @Decription
 * @Author yangsj
 * @Date 2020/2/7 10:38 下午
 **/
public class ThreadDemo implements Runnable {
    public /*volatile*/ boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag:"+flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
