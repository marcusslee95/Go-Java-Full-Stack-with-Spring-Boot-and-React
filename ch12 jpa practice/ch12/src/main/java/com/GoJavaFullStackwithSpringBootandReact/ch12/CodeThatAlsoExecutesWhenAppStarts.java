package com.GoJavaFullStackwithSpringBootandReact.ch12;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.GoJavaFullStackwithSpringBootandReact.ch12.classesThatInteractWithDB.InterfaceThatInteractsWithDBOnBehalfOfUserObjects;
import com.GoJavaFullStackwithSpringBootandReact.ch12.entity.User;

@Component //"hey spring create an instance of this class and manage it"
public class CodeThatAlsoExecutesWhenAppStarts  implements CommandLineRunner{

	private static final Logger log = 
			LoggerFactory.getLogger(CodeThatExecutesWhenAppStarts.class);
	
	@Autowired //"hey spring, when you create instance of CodeThatExecutesWhenAppStarts create an instance of this dependency class and pass it into CodeThatExecutesWhenAppStarts's constructor 
	private InterfaceThatInteractsWithDBOnBehalfOfUserObjects instanceOfInterfaceThatInteractsWithDBOnBehalfOfUserObjects;
	
	@Override
	public void run(String... args) throws Exception {
		User user = new User("Jill", "master");
		instanceOfInterfaceThatInteractsWithDBOnBehalfOfUserObjects.save(user);
		//New User is stored in db : User [id=2, name=Jill, role=master]
		log.info("New User is stored in db : " + user);
		
		Optional<User> userWithIDOne = instanceOfInterfaceThatInteractsWithDBOnBehalfOfUserObjects.findById(1L); // 1L is just way to say long type 1. Optional must be in case nothing comes back
		log.info("Retrieved this User : " + userWithIDOne);
		
		List<User> allUsers = instanceOfInterfaceThatInteractsWithDBOnBehalfOfUserObjects.findAll();
		log.info("All Users : " + allUsers);
		
		instanceOfInterfaceThatInteractsWithDBOnBehalfOfUserObjects.delete(user);
	}
}
