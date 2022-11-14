package com.spring.ex02;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest2 {
	public static void main(String[] args)
	{	
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		PersonService person = (PersonService) factory.getBean("personService1");
		//personService person = new PersonServiceImpl();
		person.sayHello();
		System.out.println();
		
		PersonService person2 = (PersonService) factory.getBean("personService2");
		person2.sayHello();
	}
}

