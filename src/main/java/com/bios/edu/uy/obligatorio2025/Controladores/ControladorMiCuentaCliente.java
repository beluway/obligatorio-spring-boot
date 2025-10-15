package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
@RequestMapping("/micuentaC")
public class ControladorMiCuentaCliente {
    
    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) {
       
        modelo.addAttribute("usuarioLoguedo", usuarioLogueado);

        return "/micuentaC/ver";
    }
    

    @PostMapping("/ver")
    public String postMethodName() {
     
        return "/micuentaC/ver";
    }
        

}