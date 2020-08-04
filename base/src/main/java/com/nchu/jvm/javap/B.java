package com.nchu.jvm.javap;

/**
 * @Decription 字节码学习
 *
 *  当前目录编译：javac -encoding utf-8  -g:lines -g:vars B.java
 *
 *  字节码：javap -p -v B
 *  Classfile /D:/workspace/MyWork/examples/base/src/main/java/com/nchu/jvm/javap/B.class
 *   Last modified 2020-8-4; size 484 bytes
 *   MD5 checksum 8b351af2962543d07fde2c9c819f6d9f
 * public class com.nchu.jvm.javap.B
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    //  Constant Pool（常量池），这些内容，就存放于我们的 Metaspace 区域，属于非堆。
 *    //  常量池包含 .class 文件常量池、运行时常量池、String 常量池等部分，大多是一些静态内容。
 *    #1 = Methodref          #7.#24         // java/lang/Object."<init>":()V
 *    #2 = Fieldref           #6.#25         // com/nchu/jvm/javap/B.a:I
 *    #3 = Fieldref           #6.#26         // com/nchu/jvm/javap/B.C:J
 *    #4 = Long               1111l
 *    #6 = Class              #27            // com/nchu/jvm/javap/B
 *    #7 = Class              #28            // java/lang/Object
 *    #8 = Utf8               a
 *    #9 = Utf8               I
 *   #10 = Utf8               C
 *   #11 = Utf8               J
 *   #12 = Utf8               <init>
 *   #13 = Utf8               ()V
 *   #14 = Utf8               Code
 *   #15 = Utf8               LineNumberTable
 *   #16 = Utf8               LocalVariableTable
 *   #17 = Utf8               this
 *   #18 = Utf8               Lcom/nchu/jvm/javap/B;
 *   #19 = Utf8               test
 *   #20 = Utf8               (J)J
 *   #21 = Utf8               num
 *   #22 = Utf8               ret
 *   #23 = Utf8               <clinit>
 *   #24 = NameAndType        #12:#13        // "<init>":()V
 *   #25 = NameAndType        #8:#9          // a:I
 *   #26 = NameAndType        #10:#11        // C:J
 *   #27 = Utf8               com/nchu/jvm/javap/B
 *   #28 = Utf8               java/lang/Object
 * {
 *   private int a;
 *     descriptor: I
 *     flags: ACC_PRIVATE
 *
 *   static long C;
 *     descriptor: J
 *     flags: ACC_STATIC
 *
 *   public com.nchu.jvm.javap.B();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=2, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: aload_0
 *          5: sipush        1234
 *          8: putfield      #2                  // Field a:I
 *         11: return
 *       LineNumberTable:
 *         line 10: 0
 *         line 11: 4
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      12     0  this   Lcom/nchu/jvm/javap/B;
 *
 *   public long test(long);
 *     descriptor: (J)J
 *     flags: ACC_PUBLIC
 *     Code:
 *     // 注意 stack 字样，它此时的数值为 4，表明了 test 方法的最大操作数栈深度为 4。JVM 运行时，会根据这个数值，来分配栈帧中操作栈的深度。
 *     // locals 变量存储了局部变量的存储空间。它的单位是 Slot（槽），可以被重用。其中存放的内容，包括：
 *        this
 *        方法参数
 *        异常处理器的参数
 *        方法体中定义的局部变量
 *     // args_size 就比较好理解。它指的是方法的参数个数，因为每个方法都有一个隐藏参数 this，所以这里的数字是 2。
 *       stack=4, locals=5, args_size=2
 *          0: aload_0                           // 把第 1 个引用型局部变量推到操作数栈，这里的意思是把 this 装载到了操作数栈中。
 *          // 将栈顶的指定的对象的第 2 个实例域（Field）的值，压入栈顶。#2 就是指的我们的成员变量 a。
 *          1: getfield      #2                  // Field a:I
 *          4: i2l                               // 将栈顶 int 类型的数据转化为 long 类型，这里就涉及我们的隐式类型转换了。图中的信息没有变动，不再详解介绍。
 *          5: lload_1                           // 将第一个局部变量入栈。也就是我们的参数 num。
 *          6: ladd                              // 把栈顶两个 long 型数值出栈后相加，并将结果入栈。
 *          // 根据偏移获取静态属性的值，并把这个值 push 到操作数栈上。
 *          7: getstatic     #3                  // Field C:J
 *         10: ladd                              // 再次执行 ladd。
 *         11: lstore_3                          // 把栈顶 long 型数值存入第 4 个局部变量。还记得我们上面的图么？slot 为 4，索引为 3 的就是 ret 变量。
 *         12: lload_3                           // 正好与上面相反。上面是变量存入，我们现在要做的，就是把这个变量 ret，压入虚拟机栈中。
 *         13: lreturn                           // 从当前方法返回 long。
 *       LineNumberTable: //该属性的作用是描述源码行号与字节码行号（字节码偏移量）之间的对应关系
 *         line 16: 0
 *         line 17: 12
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      14     0  this   Lcom/nchu/jvm/javap/B;
 *             0      14     1   num   J
 *            12       2     3   ret   J
 *
 *   static {};
 *     descriptor: ()V
 *     flags: ACC_STATIC
 *     Code:
 *       stack=2, locals=0, args_size=0
 *          0: ldc2_w        #4                  // long 1111l
 *          3: putstatic     #3                  // Field C:J
 *          6: return
 *       LineNumberTable:
 *         line 13: 0
 * }
 * @Author yangsj
 * @Date 2020/8/4 14:54
 **/
public class B {
    private int a = 1234;

    static long C = 1111;

    public long test(long num){
        long ret = this.a + num + C;
        return ret;
    }

}
