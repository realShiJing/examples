package com.nchu.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(
		serviceName="HelloServerService",
		portName="HelloServer",
		name="HelloServer",
		targetNamespace="server.nchu.com"
)
public class HelloServer {
	/**
	 * 1.???????public
	 * 2.???final??
	 * 3.????????
	 * 4.??????????
	 * @param name
	 * @return
	 */
	@WebMethod(exclude=false)
	public String sayHello(String name){
		return name + " hello!";
	}

	@WebMethod(exclude=true)
	public @WebResult(name="byeResult") String sayBye(@WebParam(name="personName") String name){
		return name + " bye!";
	}



}
