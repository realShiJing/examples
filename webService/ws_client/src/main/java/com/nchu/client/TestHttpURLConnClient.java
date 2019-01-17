package com.nchu.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class TestHttpURLConnClient {
	
	public static void main(String[] args) throws Exception {
		//定义webservice的URL
		URL url = new URL("http://127.0.0.1:8080/hello?wsdl");
		//打开连接获得URLConnection
		URLConnection uc = url.openConnection();
		//强转成HttpURLConnection
		HttpURLConnection httpuc = (HttpURLConnection)uc;
		//打开输入输出的开关
		httpuc.setDoInput(true);
		httpuc.setDoOutput(true);
		//设置请求方式
		httpuc.setRequestMethod("POST");
		//设置content-type  text/xml;charset=UTF-8
		httpuc.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
		//组装消息体
		String data = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:q0=\"http://server.rl.com/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">"
				+"<soapenv:Body>"
				+" <q0:sayBye>"
				+"	<arg0>wangwu</arg0>"
				+" </q0:sayBye>"
				+" </soapenv:Body>"
				+"</soapenv:Envelope>";
		//根据HttpURLConnection获得输出流
		OutputStream out = httpuc.getOutputStream();
		//用输出流把消息发送到服务端
		out.write(data.getBytes());
		//如果请求成功
		if(httpuc.getResponseCode() == 200){
			//获得输入流
			InputStream in = httpuc.getInputStream();
			//使用输入缓冲区
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//读取响应的消息
			StringBuffer sb = new StringBuffer();
			String line = null;
			while((line = br.readLine()) != null){
				sb.append(line);
			}
			//解析消息，定义SAXReader对象
			SAXReader  reader = new SAXReader();
			//获得文档对象
			Document doc = reader.read(new StringReader(sb.toString()));
			//使用XPath的方式获得到return这个元素的集合
			List<Element> eList = doc.selectNodes("//return");
			//遍历元素集合
			for(Element ele : eList){
				System.out.println(ele.getText());
			}


		}
		
	}

}
