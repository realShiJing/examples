package com.nchu.jvm.invoke;

/**
 * @Decription 虚拟机中提供了以下几条方法调用指令
 * 普通调用指令：
 * 1.invokestatic：调用静态方法，解析阶段确定唯一方法版本；
 * 2.invokespecial:调用<init>方法、私有及父类方法，解析阶段确定唯一方法版本；
 * 3.invokevirtual 调用所有虚方法；（final修饰的方法除外）
 * 4.invokeinterface：调用接口方法；
 * 动态调用指令（Java7新增）：
 * 5.invokedynamic：动态解析出需要调用的方法，然后执行 .
 * 前四条指令固化在虚拟机内部，方法的调用执行不可人为干预，而invokedynamic指令则支持由用户确定方法版本。
 * @Author yangsj
 * @Date 2020/8/11 15:43
 **/

class Father{
    Father(){
        System.out.println("Father默认构造方法");
    }
    public static void showStatic(String s){
        System.out.println("Father show static " + s);
    }

    public final void showFinal(){
        System.out.println("Father show final ");
    }

    public void showCommon(){
        System.out.println("Father show common");
    }
}
public class Son extends Father {
    // invokespecial
    Son(){
        super();
    }
    // invokespecial
    Son(int age){
        this();
    }

    public static void main(String[] args) {

    }
    //不是重写的父类方法，因为静态方法不能被重写
    public static void showStatic(String s){
        System.out.println("Son show static " + s);
    }

    private void showPrivate(String s){
        System.out.println("Son show private " + s);
    }

    public void show(){
        // invokestatic
        showStatic("大头儿子");
        // invokestatic
        super.showStatic("小头爸爸");
        // invokespecial
        showPrivate("hello");
        // invokespecial
        super.showCommon();
        //  invokevirtual 因为此方法声明有final 不能被子类重写，所以也认为该方法是非虚方法
        showFinal();
        // 虚方法如下
        // invokevirtual
        showCommon();// 没有显式加super，被认为是虚方法，因为子类可能重写showCommon

        MethodInterface in = null;
        // invokeinterface
        in.methodA(); // 不确定接口实现类是哪一个 需要重写
    }


}
interface MethodInterface{
    void methodA();
}
