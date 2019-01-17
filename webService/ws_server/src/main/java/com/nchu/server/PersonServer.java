package com.nchu.server;

import com.nchu.model.Person;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService

public class PersonServer {

	List<Person> pList = new ArrayList<Person>();

	/**
	 * 添加人
	 * @param person
	 */
	public void addPerson(Person person){
		pList.add(person);
	}
	/**
	 * 获得所有的人
	 * @return
	 */
	public List<Person> getPersonAll(){
		return pList;
	}

	public static void main(String[] args) {
		Endpoint.publish("http://127.0.0.1:6060/person", new PersonServer());
	}

}

