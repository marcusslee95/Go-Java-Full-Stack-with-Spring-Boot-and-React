package com.GoJavaFullStackwithSpringBootandReact.ch12;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.GoJavaFullStackwithSpringBootandReact.ch12.classesThatInteractWithDB.ClassThatInteractsWithDBOnBehalfOfUserObjects;
import com.GoJavaFullStackwithSpringBootandReact.ch12.entity.User;

@Component //"hey spring create an instance of this class and manage it"
public class CodeThatExecutesWhenAppStarts implements CommandLineRunner {//CommandLineRunner just means this class runs upon app starting up

	@Autowired //"hey spring, when you create instance of CodeThatExecutesWhenAppStarts create an instance of this dependency class and pass it into CodeThatExecutesWhenAppStarts's constructor 
	ClassThatInteractsWithDBOnBehalfOfUserObjects instanceOfClassThatInteractsWithDBOnBehalfOfUserObjects;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jack", "admin");
		instanceOfClassThatInteractsWithDBOnBehalfOfUserObjects.insert(user);
		
	}
	
	//code to log that we succesfully stored object into db -- though currently haven't set up db so we haven't learned where it's getting stored rn
	
	
}
