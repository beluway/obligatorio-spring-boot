package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.consultores;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/consultores")
public class ControladorConsultores {
 


  @GetMapping("/main")
    public String consultorCrear()
    {
    
        return "consultores/main";
        
    }

     @GetMapping("/crear")
    public String consultorCrear(@ModelAttribute consultores consultor)
    {
    
        return "consultores/crear";
        
    }
 

    @PostMapping("/crear")
    public String consultorCrear (@ModelAttribute @Valid consultores consultor, Model modelo, BindingResult resultado) 
    {           
        return "redirect:/consultores/crear";
    }


    @GetMapping("/eliminar")

    public String consultorEliminar(@ModelAttribute consultores consultor) {
      
        return "consultores/eliminar";

    }

    @PostMapping("/eliminar")
    public String consultorEliminar(@ModelAttribute @Valid consultores consultor, Model modelo, BindingResult resultado)  {
              
        return "redirect:/consultores/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String consultorModificar(@ModelAttribute consultores consultor) {
      
        
        return "consultores/modificar";
    }
    
    @PostMapping("/modificar")
    public String consultorModificar(@ModelAttribute @Valid consultores consultor, Model modelo, BindingResult resultado) {
       
        return "redirect:/consultores/modificar";
    }
    

    @GetMapping("/ver")    
    public String consultorVer(@ModelAttribute consultores consultor) {
       

        return "consultores/ver";
    }   

}
