package com.nchu.jvm.runtimedata;

import java.util.Date;

/**
 * @Decription 方法返回地址测试
 * 在字节码指令中，返回指令包含ireturn（当返回值是boolena、byte、char、short和int类型时使用）、lreturn、freturn、dreturn以及areturn(引用类型的)
 *
 * 另外还有一个return指令供声明为void的方法、实例初始化方法、类和接口的初始化方法使用
 * @Author yangsj
 * @Date 2020/8/11 11:53
 **/
public class ReturnAddressTest {
    //  ireturn
    public boolean methodBoolean(){return false;}
    //  ireturn
    public byte methodByte(){return 0;}
    //  ireturn
    public short methodShort(){return 0;}
    //  ireturn
    public char methodChar(){return 0;}
    //  ireturn
    public int methodInt(){return 0;}

    // lreturn
    public long methodLong(){return 0L;}

    // fretrun
    public float methodFlot(){return 0.0f;}

    // dreturn
    public double methodDouble(){return 0.0;}

    // areturn
    public String  methodString(){return null;}
    // areturn
    public Date methodDate(){return null;}

    // return
    public void methodVoid(){}

    // 异常处理表 Exception table 指定了发生异常后执行的字节码
    public int methodException(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        return 0;
    }

}
