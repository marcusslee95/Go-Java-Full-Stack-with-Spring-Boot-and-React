package com.example.ch5HelloWorld.todo;

import java.util.Date;

public class Todo {
	private int id;
	private String username;
	private String description;
	private boolean isCompleted;
	private Date targetDate;
	
	public void setId(int id) {
		this.id = id;
	}

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
