package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioUsuarios;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class ControladorIndex {

    @GetMapping("/index") 
    public String index() {
         
        return "index";       
    }    

    
}
