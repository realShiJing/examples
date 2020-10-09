package com.nchu.dubbo.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Decription 加载上述配置文件，创建全部 Log 接口实现的实例，并执行其 log()
 * @Author yangsj
 * @Date 2020/10/3 1:15 下午
 **/
public class App {
    public static void main(String[] args) {
        ServiceLoader<Log> load = ServiceLoader.load(Log.class);
        Iterator<Log> iterator = load.iterator();

        while (iterator.hasNext()) {
            Log log = iterator.next();
            log.log("JDK SPI");
        }
    }
}
