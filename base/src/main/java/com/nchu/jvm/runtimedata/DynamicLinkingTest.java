package com.nchu.jvm.runtimedata;

/**
 * @Decription 动态链接测试
 * javac -encoding utf-8  -g:lines -g:vars DynamicLinkingTest.java
 * javap -p -v DynamicLinkingTest.class
 * @Author yangsj
 * @Date 2020/8/11 11:44
 **/
public class DynamicLinkingTest {
    int num = 10;
    public void methodA(){
        System.out.println("methodA");
    }

    /**
     *  每一个栈帧内部都包含一个指向运行时常量池Constant pool或该栈帧所属方法的引用。
     * Constant pool:
     *    #1 = Methodref          #9.#21         // java/lang/Object."<init>":()V
     *    #2 = Fieldref           #8.#22         // com/nchu/jvm/runtimedata/DynamicLinkingTest.num:I
     *    #3 = Fieldref           #23.#24        // java/lang/System.out:Ljava/io/PrintStream;
     *    #4 = String             #19            // methodA
     *    #5 = Methodref          #25.#26        // java/io/PrintStream.println:(Ljava/lang/String;)V
     *    #6 = String             #20            // methodB
     *    #7 = Methodref          #8.#27         // com/nchu/jvm/runtimedata/DynamicLinkingTest.methodA:()V
     *    #8 = Class              #28            // com/nchu/jvm/runtimedata/DynamicLinkingTest
     *    #9 = Class              #29            // java/lang/Object
     *   #10 = Utf8               num
     *   。。。。
     *
     *
     *  Code:
     *       stack=3, locals=1, args_size=1
     *          0: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
     *          3: ldc           #6                  // String methodB
     *          5: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
     *          8: aload_0
     *          9: invokevirtual #7                  // Method methodA:()V ,将符号引用转换为常量池中的直接引用 Constant pool: #7 = Methodref          #8.#27         // com/nchu/jvm/runtimedata/DynamicLinkingTest.methodA:()V
     *         12: aload_0
     *         13: dup
     *         14: getfield      #2                  // Field num:I
     *         17: iconst_1
     *         18: iadd
     *         19: putfield      #2                  // Field num:I
     *         22: return
     */
    public void methodB(){
        System.out.println("methodB");
        methodA();
        num ++;
    }
}
