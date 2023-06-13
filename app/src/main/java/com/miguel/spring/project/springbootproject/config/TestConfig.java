package com.miguel.spring.project.springbootproject.config;

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

import com.miguel.spring.project.springbootproject.entities.User;
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

    }



    
}
