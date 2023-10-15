package com.miguel.spring.project.springbootproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.miguel.spring.project.springbootproject.repositories.UserRepository;
import com.miguel.spring.project.springbootproject.services.exceptions.DataBaseException;
import com.miguel.spring.project.springbootproject.services.exceptions.ResourceNotFoundException;
import com.miguel.spring.project.springbootproject.entities.User;
// this class in package "services" is used to connect the database to controlles
// for that, the "UserService" needs a dependency to userRepository
//@Component 
@Service
public class UserService {

    // This annotation is to Spring do the dependency injection the automatic way
    @Autowired
    private UserRepository repository;

    // creates a method that return all users in the DB
    public List<User> findAll()
    {
        return repository.findAll();

        // Now we go to the UserResource, the User Controller, we change the findAll method
    }


    public User findById(Long id)
    {
        Optional<User> obj = repository.findById(id);
        // the option ".get()" from Optional goes returns User
        // In this case we use the orElseThrow to thorw a exception
        //we use a labda exception to throw the our exception passing the id of object that not exist
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }


    /// saves a user in the database
    public User insert(User obj)
    {
        return repository.save(obj);

    }

    /// Delete a user
    public void delete(Long id)
    {
        try{
            repository.deleteById(id);
        }
        catch ( EmptyResultDataAccessException e)
        {
            throw new ResourceNotFoundException(id);
        }
        catch ( DataIntegrityViolationException e)
        {
            throw new DataBaseException(e.getMessage());
        }
    }

    /// Update a User
    public User update(Long id, User obj)
    {
        // this method we catch the user by id from the repository
        // this method just prepare the object for us, but do not save this object in the Database
        User entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);

    }


    private void updateData(User entity, User obj) {

        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());

    }


}

// IMPORTANT: this class go inject dependencys in other class, for that, we need register the class with some annotations "@Component"
// This annotation informs that class is a Spring component and can be injected in other classes 
// Other annotations that @Service, for register one service