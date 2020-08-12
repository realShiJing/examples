package com.nchu.jvm.invoke;

import org.junit.Test;

/**
 * @Decription 动态分派（重写）
 *
 *  0 new #2 <com/nchu/jvm/invoke/DynamicDispatch$Man>
 *  3 dup
 *  4 aload_0
 *  5 invokespecial #3 <com/nchu/jvm/invoke/DynamicDispatch$Man.<init>>
 *  8 astore_1
 *  9 new #4 <com/nchu/jvm/invoke/DynamicDispatch$Woman>
 * 12 dup
 * 13 aload_0
 * 14 invokespecial #5 <com/nchu/jvm/invoke/DynamicDispatch$Woman.<init>>
 * 17 astore_2
 * 18 aload_1
 * 19 invokevirtual #6 <com/nchu/jvm/invoke/DynamicDispatch$Human.sayHello> // invokevirtual 指令指向常量池符号引用 #6
 * 22 aload_2
 * 23 invokevirtual #6 <com/nchu/jvm/invoke/DynamicDispatch$Human.sayHello> // invokevirtual 指令指向常量池符号引用 #6
 * 26 new #4 <com/nchu/jvm/invoke/DynamicDispatch$Woman>
 * 29 dup
 * 30 aload_0
 * 31 invokespecial #5 <com/nchu/jvm/invoke/DynamicDispatch$Woman.<init>>
 * 34 astore_1
 * 35 aload_1
 * 36 invokevirtual #6 <com/nchu/jvm/invoke/DynamicDispatch$Human.sayHello>
 * 39 return
 * @Author yangsj
 * @Date 2020/8/11 16:27
 **/
public class DynamicDispatch {
    abstract class Human{
        protected abstract void sayHello();
    }

    class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("man say hello!");
        }
    }

    class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello!");
        }
    }




    /**
     * @Description
     * @Author yangsj
     * @Date 2020/8/11 16:41
     **/
    @Test
    public void test() {
        Human man = new Man();
        Human woman = new Woman();

        // 下面两行代码解析为字节码后，无论是指令还是参数完全一样，
        // 但是这两句指令最终执行的目标方法并不相同
        man.sayHello();
        woman.sayHello();

        man = new Woman();
        man.sayHello();
    }
}
