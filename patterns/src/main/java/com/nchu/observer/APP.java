package com.nchu.observer;

import org.junit.Test;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/21 17:30
 */
public class APP {
    @Test
    public void Test(){
        Blog blog = new Blog();
        blog.addObserver(new User());
        blog.publishArtical();
    }
}
