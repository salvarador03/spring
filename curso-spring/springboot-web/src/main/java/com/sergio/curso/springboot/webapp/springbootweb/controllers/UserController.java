package com.sergio.curso.springboot.webapp.springbootweb.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sergio.curso.springboot.webapp.springbootweb.models.User;


@Controller
public class UserController {

    @GetMapping("/details")
    public String details(Model model)
    {
        User user = new User("Andres", "Guzmán");
        user.setEmail("salvarador03@iescastelar.com");
        model.addAttribute("title", "Hola Mundo Spring Boot");
        model.addAttribute("user", user);
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) 
    {
        // model.addAttribute("users", users); con el método del @ModelAttribute no hace falta poner nada.
        model.addAttribute("title", "Listado de usuarios!");
        return "list";
    }

    @ModelAttribute("users")
    public List<User> usersModel()
    {
        List<User> users = Arrays.asList(
            new User("Sara Isabel", "Ramos"),
            new User("Sergio", "Alvarado", "salvarador03@iescastelar.com"),
            new User("Pablo", "Alvarado"),
            new User("Miguel Angel", "Alvarado", "malvde2@gmail.com")
        );
        return users;
    }
}
