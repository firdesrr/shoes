package com.example.shoes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/index")
    public String showHome(){
        return "/index";
    }
}
