package com.sergio.curso.springboot.app.aop.springbootaop.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.sergio.curso.springboot.app.aop.springbootaop.services.GreetingService;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greeting;

    @GetMapping("/greeting")
    public ResponseEntity<?> greeting() {
        return ResponseEntity.ok(Collections.singletonMap("greetings",greeting.sayHello("Sergio", "Hola que tal!")));
    }

    @GetMapping("/greeting-error")
    public ResponseEntity<?> greetingError() {
        return ResponseEntity.ok(Collections.singletonMap("greetings",greeting.sayHelloError("Sergio", "Hola que tal!")));
    }
    
}
