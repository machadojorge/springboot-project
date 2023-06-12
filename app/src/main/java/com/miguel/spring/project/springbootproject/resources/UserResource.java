package com.miguel.spring.project.springbootproject.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.spring.project.springbootproject.entities.User;

// This controller goes test the class User

//This is necessary to explain that class is a controller from class User
// This controller has acced through "localhost:8080/users"
@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    // o ResponseEntity é um tipo especificpo do Spring para receber recursos da Web
    // This word @GetMapping os necessary for indicate this method is 
   @GetMapping
    public ResponseEntity <User> findAll()
    {
        User u = new User(1L, "Maria", "maria@gmail.com", "999000999", "12345");
        return ResponseEntity.ok().body(u);
    }
}
