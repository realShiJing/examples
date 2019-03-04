package com.nchu.learn.utils.netty;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 传统socket服务端
 * @author yangsj
 *
 * 传统IO的特点：
 * 1、单线程情况下只能有一个客户端
 * 2、用线程池可以有多个客户端连接，但是非常消耗性能
 */
public class OioServer {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {

		ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
		//创建socket服务,监听10101端口
		ServerSocket server=new ServerSocket(10101);
		System.out.println("服务器启动！");
		while(true){
			//获取一个套接字（阻塞）
			final Socket socket = server.accept();
			System.out.println("来个一个新客户端！");
			newCachedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					//业务处理
					handler(socket);
				}
			});
			
		}
	}
	
	/**
	 * 读取数据
	 * @param socket
	 * @throws Exception
	 */
	public static void handler(Socket socket){
			try {
				byte[] bytes = new byte[1024];
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				int len = -1;
				while( (len = inputStream.read(bytes))!= -1){
						System.out.println(new String(bytes, 0, len));
						outputStream.write("Hello\n".getBytes());
					}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					System.out.println("socket关闭");
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
}
