package com.nchu.jvm.runtimedata;

/**
 * @Decription 操作数栈测试
 * @Author yangsj
 * @Date 2020/8/11 9:41
 **/
public class OperandStackTest {

    /**
     * @Description
     * 字节码指令信息：
     * 0 bipush 15 // 15压入操作数栈
     * 2 istore_1  // 将操作数栈顶 （15） 出栈，加载进局部变量表索引为1的slot位置
     * 3 bipush 8  // 8 压入操作数栈
     * 5 istore_2  // 将操作数栈顶 （8）出栈，加载进局部变量表索引为2的slot位置
     * 6 iload_1   // 将局部变量表索引为 1 的slot位置数据（15）压入操作数栈
     * 7 iload_2   // 将局部变量表索引为 2 的slot位置数据（8）压入操作数栈
     * 8 iadd      // 将操作数栈中最接近栈顶的两个元素出栈并相加，然后将相加的结果（23）入栈
     * 9 istore_3  // 将相加的结果（23）加载进局部变量表
     * 10 return   // 返回调用者的PC的值
     * @Author yangsj
     * @Date 2020/8/11 10:19
     **/
    public void testAddOperation(){
        byte i = 15;
        int j = 8;
        int k = i + j;
    }
}
