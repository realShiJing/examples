package com.nchu.gc.oom;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import java.io.OutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * @Decription 测试元空间内存溢出
 *
 *  -XX:MetaspaceSize=16M -XX:MaxMetaspaceSize=16M 元空间大小设置为16M
 * 堆一般都是指定大小的，但元空间不是。所以如果元空间发生内存溢出会更加严重，会造成操作系统的内存溢出。
 * 我们在使用的时候，也会给它设置一个上限 for safe。
 *
 * 元空间溢出主要是由于加载的类太多，或者动态生成的类太多。
 * 下列代码使用 Java 自带的动态代理类，不断的生成新的 class。
 *
 * 启动后访问http://localhost:8888 触发后，它将会发生元空间溢出。
 * @Author yangsj
 * @Date 2020/8/13 17:04
 **/
public class MetaspaceOOMTest {

    public interface Facade {
       void m(String input);
   }
   public static class FacadeImpl implements Facade {
       @Override
       public void m(String name) {
       }
   }
   public static class MetaspaceFacadeInvocationHandler implements InvocationHandler {
       private Object impl;
       public MetaspaceFacadeInvocationHandler(Object impl) {
           this.impl = impl;
       }
       @Override
       public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
           return method.invoke(impl, args);
       }
   }
   private static Map<String, Facade> classLeakingMap = new HashMap<String, Facade>();
   private static void oom(HttpExchange exchange) {
       try {
           String response = "oom begin!";
           exchange.sendResponseHeaders(200, response.getBytes().length);
           OutputStream os = exchange.getResponseBody();
           os.write(response.getBytes());
           os.close();
       } catch (Exception ex) {
       }
       try {
           for (int i = 0; ; i++) {
               String jar = "file:" + i + ".jar";
               URL[] urls = new URL[]{new URL(jar)};
               URLClassLoader newClassLoader = new URLClassLoader(urls);
               Facade t = (Facade) Proxy.newProxyInstance(newClassLoader,
                                               new Class<?>[]{Facade.class},
                       new MetaspaceFacadeInvocationHandler(new FacadeImpl()));
               classLeakingMap.put(jar, t);
           }
       } catch (Exception e) {
       }
   }
   private static void srv() throws Exception {
       HttpServer server = HttpServer.create(new InetSocketAddress(8888), 0);
       HttpContext context = server.createContext("/");
       context.setHandler(MetaspaceOOMTest::oom);
       server.start();
   }
   public static void main(String[] args) throws Exception {
       srv();
   }
}
