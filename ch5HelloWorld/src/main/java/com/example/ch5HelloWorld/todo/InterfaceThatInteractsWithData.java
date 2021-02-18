package com.example.ch5HelloWorld.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //this will be used to do db interactions
public interface InterfaceThatInteractsWithData extends JpaRepository<Todo, Integer>{
	List<Todo> findByUsername(String username); //JPA will recognize we created this custom method -> guess what it does -> and creates custom implementation for it.
}
