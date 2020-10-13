package com.nchu.dubbo.spi.dubbo.app;

import com.nchu.dubbo.spi.dubbo.api.Log;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.Set;

/**
 * @Description
 * @Author yangsj
 * @Date 2020/10/10 11:48 上午
 **/
public class App {
    /**
     * @Description
     * @Author yangsj
     * @Date 2020/10/10 11:48 上午
     **/
    public static void main(String[] args) {
        // 获取扩展加载器
        ExtensionLoader<Log> extensionLoader = ExtensionLoader.getExtensionLoader(Log.class);
        // 获取所有的扩展实现的key
        Set<String> supportedExtensions = extensionLoader.getSupportedExtensions();
        for (String supportedExtension : supportedExtensions) {
            // 获取所有扩展实现
            Log log = extensionLoader.getExtension(supportedExtension);
            log.log("hello world!");
        }
        System.out.println("-------------------------------------------");
        // dubbo中所有的注册信息都是通过URL的形式进行处理的
        // 这里url的格式只要求问号 ？后的内容
        // 1.第一个参数是@SPI注解的扩展接口名，接口是驼峰命名的话，这里使用 . 来拼接；例如：HelloService ==> hello.service
        // 2.第二个参数是SPI文件中的扩展实现的key
        // 如果指定扩展实现，默认使用注解@SPI的value值
        // 如果也未指定注解@SPI的value值，则会报错:未找到扩展实现类的key值
        // java.lang.IllegalStateException: Failed to get extension (com.nchu.dubbo.spi.dubbo.api.Log) name from url (test://localhost/hello) use keys([log])

        URL url = URL.valueOf("test://localhost/hello?log=log4j");

        //通过URL的方式对扩展点来进行动态选择。
        Log log = ExtensionLoader.getExtensionLoader(Log.class).getAdaptiveExtension();
        log.log(url,"hello adaptive!");
    }
}