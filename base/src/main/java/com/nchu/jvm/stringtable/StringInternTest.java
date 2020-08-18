package com.nchu.jvm.stringtable;

/**
 * @Decription 使用intern()测试执行效率：空间使用
 * 结论：对于程序中大量存在存在的字符串，尤其其中存在很多重复字符串时，使用intern()可以节省内存空间。
 * @Author yangsj
 * @Date 2020/8/18 9:26
 **/
public class StringInternTest {
    static final int MAX_COUNT = 1000 * 10000;
    static final String[] arr = new String[MAX_COUNT];


    /**
     * @Description 创建一千万个字符串实例，并放到数组中
     * 测试使用 intern()方法和不使用该方法消耗的时间差别
     * 并使用VisualVm工具查看两者在占用空间上的区别
     *
     * 实测：使用intern()方法时，耗时为4566ms，创建的实例远小于一千万个，内存占用比不使用intern()方法是小
     * 而不使用intern()方法时，耗时为12282ms，创建的实例超过一千万个
     * @Author yangsj
     * @Date 2020/8/18 9:36
     **/
    public static void main(String[] args) {
        Integer[] data = new Integer[]{1,2,3,4,5,6,7,8,9,10};

        long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_COUNT; i++) {
            arr[i] = new String(String.valueOf(data[i % data.length]));
//            arr[i] = new String(String.valueOf(data[i % data.length])).intern();// 4566ms

        }
        long end = System.currentTimeMillis();
        System.out.println("花费的时间为：" + (end - start));

        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
    }
}
