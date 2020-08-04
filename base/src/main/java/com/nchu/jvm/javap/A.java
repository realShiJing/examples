package com.nchu.jvm.javap;

/**
 * @Decription 字节码学习
 * 进入上级目录编译：javac -encoding utf-8  -g:lines -g:vars com/nchu/jvm/javap/A.java
 *
 *
 * 字节码：javap -p -v A
 *
 * Classfile /D:/workspace/MyWork/examples/base/src/main/java/com/nchu/jvm/javap/A.class
 *   Last modified 2020-8-4; size 695 bytes
 *   MD5 checksum 84947b371d9d2b539697d08df177cd9e
 * public class com.nchu.jvm.javap.A
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #12.#30        // java/lang/Object."<init>":()V
 *    #2 = Class              #31            // com/nchu/jvm/javap/B
 *    #3 = Methodref          #2.#30         // com/nchu/jvm/javap/B."<init>":()V
 *    #4 = Fieldref           #5.#32         // com/nchu/jvm/javap/A.b:Lcom/nchu/jvm/javap/B;
 *    #5 = Class              #33            // com/nchu/jvm/javap/A
 *    #6 = Methodref          #5.#30         // com/nchu/jvm/javap/A."<init>":()V
 *    #7 = Long               4321l
 *    #9 = Methodref          #2.#34         // com/nchu/jvm/javap/B.test:(J)J
 *   #10 = Fieldref           #35.#36        // java/lang/System.out:Ljava/io/PrintStream;
 *   #11 = Methodref          #37.#38        // java/io/PrintStream.println:(J)V
 *   #12 = Class              #39            // java/lang/Object
 *   #13 = Utf8               b
 *   #14 = Utf8               Lcom/nchu/jvm/javap/B;
 *   #15 = Utf8               <init>
 *   #16 = Utf8               ()V
 *   #17 = Utf8               Code
 *   #18 = Utf8               LineNumberTable
 *   #19 = Utf8               LocalVariableTable
 *   #20 = Utf8               this
 *   #21 = Utf8               Lcom/nchu/jvm/javap/A;
 *   #22 = Utf8               main
 *   #23 = Utf8               ([Ljava/lang/String;)V
 *   #24 = Utf8               args
 *   #25 = Utf8               [Ljava/lang/String;
 *   #26 = Utf8               a
 *   #27 = Utf8               num
 *   #28 = Utf8               J
 *   #29 = Utf8               ret
 *   #30 = NameAndType        #15:#16        // "<init>":()V
 *   #31 = Utf8               com/nchu/jvm/javap/B
 *   #32 = NameAndType        #13:#14        // b:Lcom/nchu/jvm/javap/B;
 *   #33 = Utf8               com/nchu/jvm/javap/A
 *   #34 = NameAndType        #40:#41        // test:(J)J
 *   #35 = Class              #42            // java/lang/System
 *   #36 = NameAndType        #43:#44        // out:Ljava/io/PrintStream;
 *   #37 = Class              #45            // java/io/PrintStream
 *   #38 = NameAndType        #46:#47        // println:(J)V
 *   #39 = Utf8               java/lang/Object
 *   #40 = Utf8               test
 *   #41 = Utf8               (J)J
 *   #42 = Utf8               java/lang/System
 *   #43 = Utf8               out
 *   #44 = Utf8               Ljava/io/PrintStream;
 *   #45 = Utf8               java/io/PrintStream
 *   #46 = Utf8               println
 *   #47 = Utf8               (J)V
 * {
 *   private com.nchu.jvm.javap.B b;
 *     descriptor: Lcom/nchu/jvm/javap/B;
 *     flags: ACC_PRIVATE
 *
 *   public com.nchu.jvm.javap.A();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=3, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: aload_0
 *          5: new           #2                  // class com/nchu/jvm/javap/B
 *          8: dup
 *          9: invokespecial #3                  // Method com/nchu/jvm/javap/B."<init>":()V
 *         12: putfield      #4                  // Field b:Lcom/nchu/jvm/javap/B;
 *         15: return
 *       LineNumberTable:
 *         line 8: 0
 *         line 9: 4
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      16     0  this   Lcom/nchu/jvm/javap/A;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=3, locals=6, args_size=1
 *          0: new           #5                  // class com/nchu/jvm/javap/A
 *          3: dup
 *          4: invokespecial #6                  // Method "<init>":()V
 *          7: astore_1
 *          8: ldc2_w        #7                  // long 4321l
 *         11: lstore_2
 *         12: aload_1
 *         13: getfield      #4                  // Field b:Lcom/nchu/jvm/javap/B;
 *         16: lload_2
 *         17: invokevirtual #9                  // Method com/nchu/jvm/javap/B.test:(J)J
 *         20: lstore        4
 *         22: getstatic     #10                 // Field java/lang/System.out:Ljava/io/PrintStream;
 *         25: lload         4
 *         27: invokevirtual #11                 // Method java/io/PrintStream.println:(J)V
 *         30: return
 *       LineNumberTable:
 *         line 12: 0
 *         line 13: 8
 *         line 15: 12
 *         line 17: 22
 *         line 18: 30
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      31     0  args   [Ljava/lang/String;
 *             8      23     1     a   Lcom/nchu/jvm/javap/A;
 *            12      19     2   num   J
 *            22       9     4   ret   J
 * }
 * @Author yangsj
 * @Date 2020/8/4 14:56
 **/
public class A {
    private B b = new B();

    public static void main(String[] args) {
        A a = new A();
        long num = 4321;

        long ret = a.b.test(num);

        System.out.println(ret);
    }
}
