package com.miguel.spring.project.springbootproject.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miguel.spring.project.springbootproject.entities.Products;
import com.miguel.spring.project.springbootproject.services.ProductsService;

// This controller goes test the class Products

//This is necessary to explain that class is a controller from class Products
// This controller has acced through "localhost:8080/Productss"
@RestController
@RequestMapping(value = "/products")
public class ProductsResource {
    
    // this is a dependency os the ProductsServide for we can receive the data from database
    @Autowired
    private ProductsService service;

    // o ResponseEntity é um tipo especificpo do Spring para receber recursos da Web
    // This word @GetMapping os necessary for indicate this method is 
   @GetMapping
    public ResponseEntity <List<Products>> findAll()
    {
        //Products u = new Products(1L, "Maria", "maria@gmail.com", "999000999", "12345");

        // Now we create a list that receives the list of Productss in the Database from "findAll()" method int the "ProductsService"
        // but, first we add a dependency of the ProductsService.

       
        List<Products> list = service.findAll();
       
        // In the Header goes the Ok, and the body goes the list of Productss
        return ResponseEntity.ok().body(list);
    }

    // in this case, we go passe one parameter to url
    @GetMapping(value = "/{id}")
    // this "PathVariable" goes say to Spring that variable in method is the variable passed in URL
    public ResponseEntity<Products> findById(@PathVariable Long id)
    {
        Products obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
