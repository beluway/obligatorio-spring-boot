package com.bios.edu.uy.obligatorio2025.Controladores;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;





@Controller
@RequestMapping("/")
public class ControladorIndex {

    @GetMapping("/index") 
    public String index() {
         
        return "index";       
    }    

    
}
