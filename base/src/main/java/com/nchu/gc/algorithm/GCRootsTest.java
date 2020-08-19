package com.nchu.gc.algorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @Decription GC Roots 分析
 * 通过Visual VM 导出两份堆存储文件，使用MAT分析局部变量作为GC Roots
 * MAT: Open Query Browser ->Java Basics -> GC Roots
 * @Author yangsj
 * @Date 2020/8/19 9:59
 **/
public class GCRootsTest {
        public static void main(String[] args) {
            List<Object> numList = new ArrayList<>();
            Date birth = new Date();

            for (int i = 0; i < 100; i++) {
                numList.add(String.valueOf(i));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("数据添加完毕，请操作：");
            new Scanner(System.in).next();
            numList = null;
            birth = null;

            System.out.println("numList、birth已置空，请操作：");
            new Scanner(System.in).next();

            System.out.println("结束");
        }
}
