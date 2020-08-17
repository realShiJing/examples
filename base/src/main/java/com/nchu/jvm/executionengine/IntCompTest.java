package com.nchu.jvm.executionengine;

/**
 * @Decription 执行引擎：测试各个模式下的执行速度
 * @Author yangsj
 * @Date 2020/8/17 15:30
 **/
public class IntCompTest {
    /**
     * 测试解释器模式、JIT编译模式及混合模式
     *  解释器模式  -Xint   : 17998ms
     *  即时编译模式-Xcomp  : 2473ms
     *  混合模式   -Xmixed : 2137ms
     *  结论：完全使用解释器模式的执行时间最长，效率最低，完全采用即时编译模式和混合模式的执行时间相差不大，效率很高
     */
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        testPrimeNumber(1000000);
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));
    }

    public static void testPrimeNumber(int count){
        for (int i = 0; i < count; i++) {
            //计算100以内的质数
            label:for(int j = 2;j <= 100;j++){
                for(int k = 2;k <= Math.sqrt(j);k++){
                    if(j % k == 0){
                        continue label;
                    }
                }
            }

        }
    }
}
