package com.example.ch5HelloWorld.HelloWorld;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HelloWorldController {
	
	//"Hello World!"
	// GET -> /hello-world
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World!";
	}
	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World!");
	}
	
	@GetMapping(path="/hello-world/path-parameter/{personName}")
	public HelloWorldBean helloWorldPathParameter(@PathVariable String personName) {
//		throw new RuntimeException("Something went wrong");
		return new HelloWorldBean(String.format("Hello %s!", personName));
	}
}
