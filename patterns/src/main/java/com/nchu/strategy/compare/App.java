package com.nchu.strategy.compare;

import org.junit.Test;

import java.util.Arrays;

/**
 * @ClassName: App 策略模式在 数组 Comparator 排序中的应用
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 11:14
 **/
public class App {

    @Test
    public void testComparatorStrategy(){
        Student[] students = {
                new Student(3, "张三"),
                new Student(1, "李四"),
                new Student(4, "王五"),
                new Student(2, "赵六")
        };
        toString(students, "排序前");

        Arrays.sort(students, new AscSortor());
        toString(students, "升序后");

        Arrays.sort(students, new DescSortor());
        toString(students, "降序后");
    }

    public static void toString(Student[] students, String desc){
        for (int i = 0; i < students.length; i++) {
            System.out.print(desc + ": " +students[i].toString() + ", ");
        }
        System.out.println();
    }
}
