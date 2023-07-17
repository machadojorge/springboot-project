package com.miguel.spring.project.springbootproject.config;

import java.time.Instant;
import java.util.Arrays;

// we are in profile test, we create the "application-test.properties" with configurations
// now, we need creates a class for this profile
// this class do not be a controller, or service or repository, just a auxiliary class with some configurations to our project
// this is very frequently

//for informs the spring that class is special for configuration, we uses the annotation
// we need informs too that profile we use. The name is the same of the file "application.properties"
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.miguel.spring.project.springbootproject.entities.Order;
import com.miguel.spring.project.springbootproject.entities.User;
import com.miguel.spring.project.springbootproject.entities.enums.OrderStatus;
import com.miguel.spring.project.springbootproject.repositories.OrderRepository;
import com.miguel.spring.project.springbootproject.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner
{
    // We need uses the dependency injection, because we need access to the DB for save the data
    // we do dependency injection through constructor, or through setMethod()
    // But in this case, the spring do that automatic
    // for that, we just creates a atribute of type userRepository and put the annotation "@Autowired"
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;
    // This method of this interface it is used for do something where the run is execute
    // in this case, ever the program is executed this method is called
    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "999888777", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "222333444", "123456");
        
        // Creates two user and save this users in the database
        // the method saveAll received a list, we use the method "Arrays.asList(u1, u2)" for create a lista
        userRepository.saveAll(Arrays.asList(u1, u2));
        
        //throw new UnsupportedOperationException("Unimplemented method 'run'");


        // In this case we creates three records, and the accociation, with attribut passed in constructor u1, u2, ~
        // referring to previous users
        // The operation "Instant.parse()" goes convert from string to a valid dateTime
        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), u2, OrderStatus.WAITING_PAYMENT);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), u1, OrderStatus.WAITING_PAYMENT);
    
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }



    
}
