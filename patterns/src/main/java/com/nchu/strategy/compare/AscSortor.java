package com.nchu.strategy.compare;

import java.util.Comparator;

/**
 * @ClassName: AscSortor 升序
 * @Description:
 * @Author: yangsj
 * @Date:Create： 2021/1/18 11:13
 **/
public class AscSortor implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
        return o1.getId() - o2.getId();
    }
}
