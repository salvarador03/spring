package com.sergio.curso.springboot.error.springbooterror;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sergio.curso.springboot.error.springbooterror.models.domain.User;

@SpringBootApplication
public class AppConfig {

    @Bean
    List<User> users() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "Sergio", "Alvarado"));
        users.add(new User(2L, "Pablo", "Alvarado"));
        users.add(new User(3L, "Miguel Angel", "Alvarado"));
        users.add(new User(4L, "Sara Isabel", "Ramos"));
        users.add(new User(5L, "Pepito", "Pérez"));
        return users;
    }

}
