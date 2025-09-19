package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.bios.edu.uy.obligatorio2025.Dominio.areas;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/areas")
public class ControladorAreas {


    @GetMapping("/crear")
    public String crear (@ModelAttribute areas area)
    {
        return "areas/crear";
    }   


    @PostMapping("/crear") 
    public String crear(@ModelAttribute @Valid areas area, Model modelo, BindingResult resultado) {
        
        return "redirect:/areas/crear";

    }
    
    
}
