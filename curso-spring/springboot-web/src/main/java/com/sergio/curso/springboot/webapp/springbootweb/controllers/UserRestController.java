package com.sergio.curso.springboot.webapp.springbootweb.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.curso.springboot.webapp.springbootweb.models.User;
import com.sergio.curso.springboot.webapp.springbootweb.models.dto.UserDto;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @GetMapping(path = "/details")
    public UserDto details(){
        UserDto userDto = new UserDto();
        User user = new User("Andres", "Guzmán");
        userDto.setUser(user);
        userDto.setTitle("Hola Mundo Spring Boot");
        return userDto;
    }

    @GetMapping(path = "/list")
    public List<User> list() {
        User user = new User("Andres", "Guzmán");
        User user2 = new User("Pepe", "Doe");
        User user3 = new User("Jhon", "Doe");

        List<User> users = Arrays.asList(user, user2, user3); 
/*      List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user2);
        users.add(user3); */
        
        return users;
    }

    @GetMapping(path = "/details-map")
    public Map<String, Object> detailsMap(){
        User user = new User("Andres", "Guzmán");
        Map<String, Object> body = new HashMap<>();
        body.put("title", "Hola Mundo Spring Boot");
        body.put("user", user);
        return body;
    }

}
