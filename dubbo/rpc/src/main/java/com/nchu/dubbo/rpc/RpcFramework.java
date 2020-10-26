package com.nchu.dubbo.rpc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Decription
 * @author william.liangf
 * @Date 2020/10/26 11:23 上午
 **/
public class RpcFramework {
    /**
     * 暴露服务
     *
     * @param service 服务实现
     * @param port 服务端口
     * @throws Exception
     */
    public static void export(final Object service, int port) throws Exception {
        if (service == null)
            throw new IllegalArgumentException("service instance == null");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port" + port);
        System.out.println("Export service" + service.getClass().getName()+ "on port" + port);
        // 创建 ServerSocket
        ServerSocket server = new ServerSocket(port);
        for(;;) {
            try {
                // 阻塞等待客户端请求
                final Socket socket = server.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            try {
                                // 从client 获取数据
                                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                                try {
                                    // 获取方法名
                                    String methodName = input.readUTF();
                                    // 获取请求参数类型
                                    Class<?>[] parameterTypes = (Class<?>[])input.readObject();
                                    // 获取请求参数
                                    Object[] arguments = (Object[])input.readObject();
                                    // 向client 输出数据
                                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                    try {
                                        // 获取调用方法
                                        Method method = service.getClass().getMethod(methodName, parameterTypes);
                                        // JDK 动态代理调用目标方法
                                        Object result = method.invoke(service, arguments);
                                        // 将方法返回结果输出给client
                                        output.writeObject(result);
                                    } catch (Throwable t) {
                                        // 如果产生异常，将异常返回给 client
                                        output.writeObject(t);
                                    } finally {
                                        output.close();
                                    }
                                } finally {
                                    input.close();
                                }
                            } finally {
                                socket.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 引用服务
     *
     * @param <T> 接口泛型
     * @param interfaceClass 接口类型
     * @param host 服务器主机名
     * @param port 服务器端口
     * @return 远程服务
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T refer(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        if (interfaceClass == null)
            throw new IllegalArgumentException("Interface class == null");
        if (! interfaceClass.isInterface())
            throw new IllegalArgumentException("The" + interfaceClass.getName() + "must be interface class!");
        if (host == null || host.length() == 0)
            throw new IllegalArgumentException("Host == null!");
        if (port <= 0 || port > 65535)
            throw new IllegalArgumentException("Invalid port" + port);
        System.out.println("Get remote service" + interfaceClass.getName() + "from server" + host + ":" + port);
        // 使用JDK动态代理，返回远程调用代理对象
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                // 创建 Socket 连接
                Socket socket = new Socket(host, port);
                try {
                    // 向Socket中写入数据
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        // 方法名
                        output.writeUTF(method.getName());
                        // 方法参数类型
                        output.writeObject(method.getParameterTypes());
                        // 方法参数
                        output.writeObject(arguments);
                        // 从Socket 中读取数据
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            // 获取远程调用执行结果
                            Object result = input.readObject();
                            if (result instanceof Throwable) {
                                throw (Throwable) result;
                            }
                            return result;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }


}
