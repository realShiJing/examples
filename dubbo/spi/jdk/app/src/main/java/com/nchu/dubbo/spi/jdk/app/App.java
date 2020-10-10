package com.nchu.dubbo.spi.jdk.app;

import com.nchu.dubbo.spi.jdk.api.Log;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Decription 加载第三方 jar 包SPI文件，创建全部 Log 接口实现的实例，并执行其 log()
 * 接口实现类所在的jar包放在主程序的classpath中
 * @Author yangsj
 * @Date 2020/10/3 1:15 下午
 **/
public class App {
    /**
     * @Description  主程序通过java.util.ServiceLoader动态装载实现模块，
     * 它通过扫描META-INF/services目录下 的配置文件找到实现类的全限定名，把类加载到JVM,最终使用该实现类完成业务功能
     * @Author yangsj
     * @Date 2020/10/10 11:17 上午
     **/
    public static void main(String[] args) {
        ServiceLoader<Log> load = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = load.iterator();

        while (iterator.hasNext()) {
            Log log = iterator.next();
            log.log("JDK SPI");
        }
    }
}
