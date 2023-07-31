package com.miguel.spring.project.springbootproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.spring.project.springbootproject.entities.User;
import com.miguel.spring.project.springbootproject.services.UserService;

// This controller goes test the class User

//This is necessary to explain that class is a controller from class User
// This controller has acced through "localhost:8080/users"
@RestController
@RequestMapping(value = "/users")
public class UserResource {
    
    // this is a dependency os the UserServide for we can receive the data from database
    @Autowired
    private UserService service;

    // o ResponseEntity é um tipo especificpo do Spring para receber recursos da Web
    // This word @GetMapping os necessary for indicate this method is 
   @GetMapping
    public ResponseEntity <List<User>> findAll()
    {
        //User u = new User(1L, "Maria", "maria@gmail.com", "999000999", "12345");

        // Now we create a list that receives the list of Users in the Database from "findAll()" method int the "UserService"
        // but, first we add a dependency of the userService.

       
        List<User> list = service.findAll();
       
        // In the Header goes the Ok, and the body goes the list of users
        return ResponseEntity.ok().body(list);
    }

    // in this case, we go passe one parameter to url
    @GetMapping(value = "/{id}")
    // this "PathVariable" goes say to Spring that variable in method is the variable passed in URL
    public ResponseEntity<User> findById(@PathVariable Long id)
    {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
