package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/micuenta")
public class ControladorMiCuenta {
    
    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) {
       
        modelo.addAttribute("usuarioLoguedo", usuarioLogueado);

        return "/micuenta/ver";
    }
    

    @PostMapping("/ver")
    public String postMethodName() {
     
        return "/micuenta/ver";
    }
    

    

}
