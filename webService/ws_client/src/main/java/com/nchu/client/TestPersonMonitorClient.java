package com.nchu.client;


import com.nchu.person.Person;
import com.nchu.person.PersonServer;
import com.nchu.person.PersonServerService;

import java.util.List;

public class TestPersonMonitorClient {
	
	public static void main(String[] args) {
		
		//����������ʵ㼯��
		PersonServerService pss = new PersonServerService();
		//���ݷ�����ʵ��ð󶨵���
		PersonServer server = pss.getPersonServerPort();
		//���þ���ҵ���߼�
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
		
		//��������˵�webservice���񷽷�
		/*server.addPerson(person);
		server.addPerson(person1);*/
		//���ò�ѯ�����˵�webservice���񷽷�
		List<Person> pList = server.getPersonAll();
		for(Person p : pList){
			System.out.println("id: "+p.getId()+"   name:"+p.getName() + "  age:"+p.getAge() + " address:"+p.getAddress());
		}
	}

}
