package com.example.ch5HelloWorld.todo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController //hey spring i'm one of the classes that receives requests and sends back responses
@CrossOrigin(origins="http://localhost:4200")
public class ToDosControllerThatAccessesDB {
	
	
	@Autowired //hey spring pass instance of this in constructor of ToDosControllerThatAccessesDB... it needs it.
	private InterfaceThatInteractsWithData instanceOfInterfaceThatInteractsWithData;
	
	@GetMapping("jpa/users/{username}/todos")
	public List<Todo> getAllTodosOfASpecificUser(@PathVariable String username) {
		return instanceOfInterfaceThatInteractsWithData.findByUsername(username);
//		return instanceOfDataInteractingClass.findAll();
	}
	
	@GetMapping("jpa/users/{username}/todos/{id}" )
	public Todo getATodo(@PathVariable String username, @PathVariable int id) {
		return instanceOfInterfaceThatInteractsWithData.findById(id).get();
//		return instanceOfDataInteractingClass.findById(id);
	}
	
	@DeleteMapping("jpa/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteOneTodo(@PathVariable String username, @PathVariable int id) {//username here is only needed for the url. We don't need to use it because we have the Todo's id anyway.
		instanceOfInterfaceThatInteractsWithData.deleteById(id);
		return ResponseEntity.noContent().build(); //some 200s status code
	}
	
	@PutMapping("jpa/users/{username}/todos/{id}" )
	public ResponseEntity<Todo> updateATodoButIfItDoesntExistCreateOne(
			@PathVariable String username, 
			@PathVariable int id,
			@RequestBody Todo todo) {//id has no purpose here. We're not using it for anything. It's only for request url, which we designated as the indicator for a particular todo
		
		todo.setUsername(username); //honestly trivial.... just something for people that aren't using UI and just sending requests directly to this web service. in which case we'd want to set username property as whatever they sent in url... as opposed to username value in Todo.... 
		
		Todo updatedOrIfNoMatchNewlyCreatedTodo = instanceOfInterfaceThatInteractsWithData.save(todo); //JPARepository save method 
		return new ResponseEntity<Todo>(updatedOrIfNoMatchNewlyCreatedTodo, HttpStatus.OK);
	}
	
	@PostMapping("jpa/users/{username}/todos") //the way I read this url is we're adding a new todo to the the many todos - which this url refers to. Also doesn't make sense to do /todos/{id} cuz that resource does not exist yet
	public ResponseEntity<Void> createATodo(
			@PathVariable String username,
			@RequestBody Todo todo
			) {
		
		todo.setUsername(username);
		Todo newlyCreatedTodoHopefullySomeoneDidntSendInTodoWithSameId = instanceOfInterfaceThatInteractsWithData.save(todo);
		
		//Location of the new Todo we just created -> because that's what we normally send back in response to post request
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newlyCreatedTodoHopefullySomeoneDidntSendInTodoWithSameId.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
