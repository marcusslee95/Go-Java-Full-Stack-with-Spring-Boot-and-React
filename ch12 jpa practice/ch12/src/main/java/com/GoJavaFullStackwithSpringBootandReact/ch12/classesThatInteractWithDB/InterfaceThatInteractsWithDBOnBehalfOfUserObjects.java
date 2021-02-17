package com.GoJavaFullStackwithSpringBootandReact.ch12.classesThatInteractWithDB;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GoJavaFullStackwithSpringBootandReact.ch12.entity.User;

public interface InterfaceThatInteractsWithDBOnBehalfOfUserObjects extends JpaRepository<User, Long>{//Spring Data JPA will provide all common db interaction methods to us

}
