package com.nchu.cas;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @Decription Unsafe Test
 * @Author yangsj
 * @Date 2020/7/28 14:40
 **/
public class UnsafeTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println(unsafe);
    }
}
