package com.GoJavaFullStackwithSpringBootandReact.ch12.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String role;
	
	protected User() {//this exists purely because JPA requires it to exist for JPA to work
		
	}
	
	public User(String name, String role) {//don't assign id because we want JPA to generate value of that for us
		super();
		this.name = name;
		this.role = role;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getRole() {
		return role;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", role=" + role + "]";
	}

	
}
