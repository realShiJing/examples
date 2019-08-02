package com.nchu.base.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertieUtils {
    private static final PropertieUtils propertieUtils = new PropertieUtils();
    private static final InputStream inStream = PropertieUtils.class.getClassLoader().getResourceAsStream("conf/xfundsFenics.properties");
    Properties prop = new Properties();

    public static PropertieUtils getInstance() {
        return propertieUtils;
    }

    public PropertieUtils(){
    }

    public  String getProperty(String key){
        try {
            prop.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(prop == null){
            return null;
        }
        return prop.getProperty(key);
    }

}
