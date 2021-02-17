package com.GoJavaFullStackwithSpringBootandReact.ch12.classesThatInteractWithDB;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.GoJavaFullStackwithSpringBootandReact.ch12.entity.User;

@Repository //it's saying "this class interacts w/DB"
@Transactional //it's saying "hey JPA you do the open and closing connections to the DB. I don't want to manually have to write em all"
public class ClassThatInteractsWithDBOnBehalfOfUserObjects {
	
	@PersistenceContext //"all the objects I manage via entityManager I'll keep track of in this thing so that if values of those objects change entityManager will automatically know"
	EntityManager entityManager; //thing in charge of interactions w/db
	
	public long insert(User user){
		entityManager.persist(user); //this would store object into db
		return user.getId(); //I don't think this is necessary. might just be convention where we return thing we stored
	}
	
}
