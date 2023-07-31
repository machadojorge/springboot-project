package com.miguel.spring.project.springbootproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miguel.spring.project.springbootproject.entities.Products;
// this class is a interface for our repository
// we needs extends this class to JPARepository<User, Long> , passed the class entitie and the type of primary key
public interface ProductsRepository extends JpaRepository<Products, Long>
{
    // we do not need  implements the class because the JPARepository has a default implementation to
    // this interface
    
}
