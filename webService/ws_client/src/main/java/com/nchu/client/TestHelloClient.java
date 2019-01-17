package com.nchu.client;


import com.nchu.hello.HelloServer;
import com.nchu.hello.HelloServerService;


public class TestHelloClient {

	public static void main(String[] args) {
		//创建服务访问点集合
		HelloServerService hss = new HelloServerService();
		//根据服务访问点获得绑定的类
		HelloServer server = hss.getHelloServer();
		//调用具体业务逻辑
		String result = server.sayHello("nchu");
		System.out.println(result);
	}

}
