package com.nchu.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @description: 单例模式应用(饿汉模式，会有同步问题)，Properties 读取文件配置信息
 * @auther: yangsj
 * @created: 2019/3/20 10:41
 */
public class PropertiesUtils {
    private static PropertiesUtils propertiesUtils = new PropertiesUtils();
    InputStream inStream = PropertiesUtils.class.getClassLoader().getResourceAsStream("conf.properties");
    Properties prop = new Properties();

    public static PropertiesUtils getInstance() {
        return propertiesUtils;
    }
    public String getProperty(String key){
        try {
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
