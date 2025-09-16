package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model ;
import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/ofertas")
public class ControladorOfertas {
    


    
    @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute ofertas ofertas, HttpSession sesion, Model modelo)
    {
         modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));   

        return "ofertas/crear";
        
    }

    @PostMapping("/crear")
    public String clienteCrear (@ModelAttribute @Valid ofertas ofertas, Model modelo, BindingResult resultado, HttpSession sesion) 
    {               
        //SE TRAE EL USUARIO LOGUEADO DESDE LA SESION
        sesion.getAttribute("usuarioLogueado");
        
        return "redirect:/ofertas/crear";
    }


    @GetMapping("/eliminar")

    public String clienteEliminar(@ModelAttribute ofertas ofertas) {
      
        return "ofertas/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Valid ofertas ofertas, Model modelo, BindingResult resultado)  {
              
        return "redirect:/ofertas/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(@ModelAttribute ofertas ofertas) {
      
        
        return "ofertas/modificar";
    }
    

    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Valid ofertas ofertas, Model modelo, BindingResult resultado) {
       
        return "redirect:/ofertas/modificar";
    }
    

    @GetMapping("/ver")    
    public String clientesVer(@ModelAttribute ofertas ofertas) {
       

        return "ofertas/ver";
    }   


      @GetMapping("/listas")    
    public String clientesVer(Model modelo) {
       
        //ACA SE SACA LA LISTA DESDE LA BASE DE DATOS

        modelo.addAttribute("listaOfertas", modelo);

        return "ofertas/listado";
    } 

}
