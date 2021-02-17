package com.example.ch5HelloWorld.todo;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service 
public class ClassThatInteractsWithData {

	private static List<Todo> todos = new ArrayList<>();
	private static int idCounter = 0;
	
	static {
		todos.add(new Todo(++idCounter, "M", "Plan VV Date", false, new Date()));
		todos.add(new Todo(++idCounter, "M", "Prep for Interview", false, new Date()));
		todos.add(new Todo(++idCounter, "M", "Clean room", false, new Date()));
	}

	public List<Todo> findAll() {
		// TODO Auto-generated method stub
		return todos;
	}

	public Todo deleteATodo(int id) {
		Todo toDoToBeDeleted = findById(id);
		if (toDoToBeDeleted != null) {
			todos.remove(toDoToBeDeleted);
			return toDoToBeDeleted;
		}
		else {
			return null;
		}
	}

	public Todo findById(int id) {
		for (Todo todo: todos) {
			if (todo.getId() == id) {
				return todo;
			}
		}
		return null;
	}

	//if matching record exists then updates. If no match then creates new
	public Todo save(Todo todoFromRequest) {
		
		if (todoFromRequest.getId() == -1 || todoFromRequest.getId() == 0) {//create new ToDo -- in Frontend wrote it such that only request with Todo of id = -1 or 0 meant we want to create a new Todo - because ones without ids get autoassigned -1 or - as id.
			todoFromRequest.setId(++idCounter); //since it's a new record want to give it latest id
			todos.add(todoFromRequest);
		}
		else {//want to update existing Todo
			deleteATodo(todoFromRequest.getId());
			todos.add(todoFromRequest); 
		}
		
		return todoFromRequest;
		
		//my previous implementation: had some edge cases that didn't work
//		boolean wasThereAMatch = false;
//		for (Todo todoInBackend: todos) {
//			if (todoInBackend.getId() == todoFromRequest.getId()) {//if there's a matching record -> then update (currently do it via ghetto way of just deleting the preexistign one w/the old values and then creating one w/same id but different values
//				deleteATodo(todoInBackend.getId());
//				todos.add(todoFromRequest); 
//				wasThereAMatch = true;
//				break;
//			}
//		}
//		
//		if (!wasThereAMatch) {//went through whole todos w/out match so no match -> then create new.
//			todoFromRequest.setId(++idCounter); //since it's a new record want to give it latest id
//			todos.add(todoFromRequest);
//		}
//		
//		return todoFromRequest;
	}

}
