package com.nchu.jvm.methodarea;


/**
 * @Decription TODO
 * @Author yangsj
 * @Date 2020/8/17 9:28
 **/
public class MethodAreaTest {
    public static void main(String[] args) {
        Order order = null;
        order.hello();
        System.out.println(order.count);
    }
}

/**
 * @Description 被声明为static final的常量number在编译的时候就被赋值了，
 * 这不同于没有被final修饰的static变量count是在类加载的准备阶段才被赋值（还记得clinit吗）。
 *   public static int count;
 *     descriptor: I
 *     flags: ACC_PUBLIC, ACC_STATIC
 *
 *   public static final int number;
 *     descriptor: I
 *     flags: ACC_PUBLIC, ACC_STATIC, ACC_FINAL
 *     ConstantValue: int 2
 * @Author yangsj
 * @Date 2020/8/17 9:33
 **/
class Order {

    public static int count = 1;
    public static final int number = 2;


    public static void hello() {
        System.out.println("hello!");
    }
}