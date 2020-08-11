package com.nchu.jvm.runtimedata;

/**
 * @Decription 局部变量表测试
 * javac -encoding utf-8  -g:lines -g:vars LocalVariablesTest.java
 * javap -p -v LocalVariablesTest.class
 * 局部变量表是一组变量值存储空间，用于存放方法参数和方法内部定义的局部变量。
 * @Author yangsj
 * @Date 2020/8/11 10:52
 **/
public class LocalVariablesTest {

    /**
     * @Description 此方法局部变量表中有三个值
     * @Author yangsj
     * @Date 2020/8/11 11:00
     **/
    // 局部变量表：方法参数 args
    public static void main(String[] args) {
        // 局部变量表：局部变量 对象引用 localVariables
        LocalVariablesTest localVariables = new LocalVariablesTest();
        // 局部变量表：基本类型 整型 num
        int num = 10 ;
        localVariables.test();
    }

    private void test(){
        int i = 20;
        System.out.println("test");
    }


    /**
     * @Description slot复用
     * 栈帧中的局部变量表中的槽位是可以重复利用的，如果一个局部变量过了其作用域，
     * 那么在其作用域之后申明的新的局部变量就很有可能会复用过期局部变量的槽位，从而达到节省资源的目的。
     * Q:下列代码对应的栈帧中局部变量表中一共有多少个slot，或者说局部变量表的长度是几？
     * A: 答案是3， this占0号、a单独占1个槽号、c重复使用了b的槽号
     *
     *   LocalVariableTable:
     *         Start  Length  Slot  Name   Signature
     *             4       4     2     b   I
     *             0      13     0  this   Lcom/nchu/jvm/runtimedata/LocalVariablesTest;
     *             2      11     1     a   I
     *            12       1     2     c   I
     * @Author yangsj
     * @Date 2020/8/11 11:02
     **/
    public void slotReuse(){
        int a = 0;
        {
            int b = 0 ;
            b = a + 1;
        }
        ////变量c使用之前已经销毁的变量b占据的slot位置
        int c = a + 1;
    }
}
