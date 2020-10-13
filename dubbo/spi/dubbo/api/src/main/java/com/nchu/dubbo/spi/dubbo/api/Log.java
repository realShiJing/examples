package com.nchu.dubbo.spi.dubbo.api;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @Description  日志接口
 * @Author yangsj
 * @Date 2020/10/3 1:04 下午
 **/
@SPI("logback")
public interface Log {
    /**
     * @Description  打印日志
     * @Author yangsj
     * @Date 2020/10/3 1:04 下午
     **/
    void log(String info);
    /**
     * @Description  自适应拓展
     * @Author yangsj
     * @Date 2020/10/10 1:43 下午
     **/
    @Adaptive
    void log(URL url,String info);
}
