package com.miguel.spring.project.springbootproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.miguel.spring.project.springbootproject.repositories.ProductsRepository;
import com.miguel.spring.project.springbootproject.entities.Products;

// this class in package "services" is used to connect the database to controlles
// for that, the "UserService" needs a dependency to userRepository
//@Component 
@Service
public class ProductsService {

    // This annotation is to Spring do the dependency injection the automatic way
    @Autowired
    private ProductsRepository repository;

    // creates a method that return all users in the DB
    public List<Products> findAll()
    {
        return repository.findAll();

        // Now we go to the UserResource, the User Controller, we change the findAll method
    }


    public Products findById(Long id)
    {
        Optional<Products> obj = repository.findById(id);
        // the option ".get()" from Optional goes returns User
        return obj.get();
    }
}

// IMPORTANT: this class go inject dependencys in other class, for that, we need register the class with some annotations "@Component"
// This annotation informs that class is a Spring component and can be injected in other classes 
// Other annotations that @Service, for register one service