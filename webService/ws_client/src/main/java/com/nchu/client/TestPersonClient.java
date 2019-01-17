package com.nchu.client;


import com.nchu.person.Person;
import com.nchu.person.PersonServer;
import com.nchu.person.PersonServerService;

import java.util.List;

public class TestPersonClient {
	

	public static void main(String[] args) {
		//创建服务访问点集合
		PersonServerService pss = new PersonServerService();
		//根据服务访问点获得绑定的类
		PersonServer server = pss.getPersonServerPort();
		//调用具体业务逻辑
		Person person = new Person();
		person.setId(1);
		person.setName("zhaoliu");
		person.setAge(20);
		person.setAddress("xiangyashan");

		Person person1 = new Person();
		person1.setId(2);
		person1.setName("tianqi");
		person1.setAge(25);
		person1.setAddress("gaolaozhang");

		//调用添加人的webservice服务方法
		server.addPerson(person);
		server.addPerson(person1);
		//调用查询所有人的webservice服务方法
		List<Person> pList = server.getPersonAll();
		for(Person p : pList){
			System.out.println("id: "+p.getId()+"   name:"+p.getName() + "  age:"+p.getAge() + " address:"+p.getAddress());
		}
	}

}
