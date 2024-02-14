package com.sergio.curso.springboot.webapp.springbootweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"", "/", "/home"})
    public String home() {
        /* return "redirect:/list" */
        return "forward:/list"; // con el return redirige y cambia la ruta, en cambio, el forward no cambia la ruta (nombre)
    }
}
