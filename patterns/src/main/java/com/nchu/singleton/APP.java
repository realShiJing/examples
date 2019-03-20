package com.nchu.singleton;

import org.junit.Test;

import java.io.IOException;

/**
 * @description:
 * @auther: yangsj
 * @created: 2019/3/20 10:47
 */
public class APP {
    public PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();
    @Test
    public void  Test() throws IOException {
        String username = propertiesUtils.getProperty("username");
        System.out.print(username);
    }
}
