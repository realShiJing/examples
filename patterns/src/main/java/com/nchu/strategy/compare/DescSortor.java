package com.nchu.strategy.compare;

import java.util.Comparator;

/**
 * @ClassName: DescSortor 降序
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 11:12
 **/
public class DescSortor implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o2.getId() - o1.getId();
    }
}
