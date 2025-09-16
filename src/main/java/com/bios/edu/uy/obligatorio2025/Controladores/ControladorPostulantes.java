package com.bios.edu.uy.obligatorio2025.Controladores;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;
import org.springframework.ui.Model;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/postulantes")
public class ControladorPostulantes {
    
      
    @GetMapping("/main")
    public String pstulanteCrear(@ModelAttribute postulantes postulante)
    {   

        return "postulantes/main";  

    }


    @GetMapping("/crear")
    public String postulanteCrear(@ModelAttribute postulantes postulante)
    {
    
        return "postulantes/crear";
        
    }

    @PostMapping("/crear")
    public String postulanteCrear (@ModelAttribute @Valid postulantes postulante,  BindingResult resultado, Model modelo) 
    {               
        return "redirect:/postulantes/crear";
    }


    @GetMapping("/eliminar")

    public String postulanteEliminar(@ModelAttribute postulantes postulante) {
      
        return "postulantes/eliminar";

    }

    @PostMapping("/eliminar")
    public String postulanteEliminar(@ModelAttribute @Valid postulantes postulante, Model modelo, BindingResult resultado)  {
              
        return "redirect:/postulantes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String postulanteModificar(@ModelAttribute postulantes postulante) {
      
        
        return "postulantes/modificar";
    }
    
    @PostMapping("/modificar")
    public String postulanteModificar(@ModelAttribute @Valid postulantes postulante, Model modelo, BindingResult resultado) {
       
        return "redirect:/postulantes/modificar";
    }
    

    @GetMapping("/ver")    
    public String postulanteVer(String usuario, Model modelo) {
       
        modelo.addAttribute("nombreUsuario", usuario);


        return "postulantes/ver";
    }   


    
    @PostMapping("/ver")    
    public String postulanteVer(@ModelAttribute postulantes postulantes, BindingResult resultado,  @RequestParam  String accion) {
       
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

    @GetMapping("/lista")
    public String listaPostulantes(Model modelo) {
       
        //SE SACA LA LISTA DE POSTULANTES DE LA BD 
        modelo.addAttribute("listaPostulantes", modelo);


        return "postulantes/lista";

    }
    

}
