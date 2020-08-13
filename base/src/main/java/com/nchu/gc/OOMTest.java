package com.nchu.gc;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Decription 模拟堆溢出
 *
 * -Xms300m -Xmx300m -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
 *
 * 启动后，浏览器访问 http://localhost:8888  将每秒钟生成 1MB 的数据，并且不会被回收，直至内存溢出
 *
 * 可以使用 jdk 自带VisualVM 安装插件Visual GC 实时监控内存动态
 *
 * @Author yangsj
 * @Date 2020/8/13 16:24
 **/
public class OOMTest { 
    public static final int _1MB = 1024 * 1024;
   static List<byte[]> byteList = new ArrayList<>();
   private static void oom(HttpExchange exchange) {
       try {
           String response = "oom begin!";
           exchange.sendResponseHeaders(200, response.getBytes().length);
           OutputStream os = exchange.getResponseBody();
           os.write(response.getBytes());
           os.close();
       } catch (Exception ex) {
       }
       for (int i = 0; ; i++) {
           byte[] bytes = new byte[_1MB];
           byteList.add(bytes);
           System.out.println(i + "MB");
           memPrint();
           try {
               Thread.sleep(1000);
           } catch (Exception e) {
           }
       }
   }
   static void memPrint() {
       for (MemoryPoolMXBean memoryPoolMXBean : ManagementFactory.getMemoryPoolMXBeans()) {
           System.out.println(memoryPoolMXBean.getName() +
                   " \t\t committed:" + memoryPoolMXBean.getUsage().getCommitted() +
                   " \t\t used:" + memoryPoolMXBean.getUsage().getUsed());
       }
   }
   private static void srv() throws Exception {
       HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
       HttpContext context = server.createContext("/");
       context.setHandler(OOMTest::oom);
       server.start();
   }
   public static void main(String[] args) throws Exception{
       srv();
   }
}
