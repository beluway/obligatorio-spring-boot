package com.bios.edu.uy.obligatorio2025.Controladores;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/postulantes")
public class ControladorPostulantes {
    
      
    @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute postulantes postulante)
    {
    
        return "postulantes/crear";
        
    }

    @PostMapping("/crear")
    public String clienteCrear (@ModelAttribute @Valid postulantes postulante, Model modelo, BindingResult resultado) 
    {               
        return "postulantes/crear";
    }


    @GetMapping("/eliminar")

    public String clienteEliminar(@ModelAttribute postulantes postulante) {
      
        return "postulantes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Valid postulantes postulante, Model modelo, BindingResult resultado)  {
              
        return "postulantes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(@ModelAttribute postulantes postulante) {
      
        
        return "postulantes/modificar";
    }
    
    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Valid postulantes postulante, Model modelo, BindingResult resultado) {
       
        return "postulantes/modificar";
    }
    

    @GetMapping("/ver")    
    public String clientesVer(@ModelAttribute postulantes postulantes) {
       

        return "postulantes/ver";
    }   


    
    @PostMapping("/ver")    
    public String clientesVer(@ModelAttribute postulantes postulantes, BindingResult resultado,  @RequestParam  String accion) {
       
        if("btn_modificar".equals(accion))
        {
            //LLAMA A PERSISTENCIA MODIFICAR

            return "redirect:/postulantes/modificar";
        }
        
        else if("btn_eliminar".equals(accion))
        {
                //ACA APARECE UN POP UP QUE PREGUNTA SI SE ELIMINA O NO

            //LLAMA A PERSISTENCIA ELIMINAR
        }

        return "postulantes/ver";
    }   

}
