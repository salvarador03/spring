package com.sergio.curso.springboot.error.springbooterror.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sergio.curso.springboot.error.springbooterror.models.domain.User;
import com.sergio.curso.springboot.error.springbooterror.services.UserService;

@RestController
@RequestMapping("/app")
public class AppController {

    @Autowired
    private UserService service;
    
    @GetMapping
    public String index() {
        int value = Integer.parseInt("hola");
        System.out.println(value);
        return "OK 200";
    }

    @GetMapping("/show/{id}")
    public ResponseEntity<?> show(@PathVariable(name = "id") Long id) {
        //User user = service.findById(id).orElseThrow(() -> new UserNotFoundException("Error, el usuario no existe"));
        Optional<User> optionalUser = service.findById(id);
        if(!optionalUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        //System.out.println(user.getLastName());
        return ResponseEntity.ok(optionalUser.orElseThrow());
    }
}
