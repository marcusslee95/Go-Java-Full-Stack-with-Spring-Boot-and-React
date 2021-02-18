package com.example.ch5HelloWorld.todo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity //Imma store objects of this type into db
public class Todo {
	
	@Id //this is going to be primary key of a record
	@GeneratedValue //and I'm not setting it. Spring you are!
	private int id;
	private String username;
	private String description;
	private boolean isCompleted;
	private Date targetDate;
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Todo() {}
	
	public Todo(int id, String username, String description, boolean isCompleted, Date targetDate) {
		super();
		this.id = id;
		this.username = username;
		this.description = description;
		this.isCompleted = isCompleted;
		this.targetDate = targetDate;
	}

	public String getUsername() {
		return username;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", username=" + username + ", description=" + description + ", isCompleted="
				+ isCompleted + ", targetDate=" + targetDate + "]";
	}
	
	

}
