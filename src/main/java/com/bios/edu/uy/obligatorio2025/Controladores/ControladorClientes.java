package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.clientes;
import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")

public class ControladorClientes {
    
 


    @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute clientes cliente)
    {
         //ENTRA ACA SOLO SI ES CONSULTOR
    
        return "clientes/crear";
        
    }

    //ACA VA FLASH ATTRIBUTES Y REDIRECT 
    @PostMapping("/crear")
    public String clienteCrear (@ModelAttribute @Valid clientes cliente, Model modelo, BindingResult resultado) 
    {               
        return "redirect:/clientes/crear";
    }


    @GetMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute clientes cliente) {
      
         //ENTRA ACA SOLO SI ES CONSULTOR

        return "clientes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Valid clientes cliente, Model modelo, BindingResult resultado)  {
              
        return "redirect:/clientes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(@ModelAttribute clientes cliente) {
      
         //ENTRA ACA SOLO SI ES CONSULTOR

        return "clientes/modificar";
    }
    
    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Valid clientes cliente, Model modelo, BindingResult resultado) {
       
        return "redirect:/clientes/modificar";
    }
    

    @GetMapping("/ver")    
    public String clientesVer(@ModelAttribute clientes cliente) {
       
         //ENTRA ACA SOLO SI ES CONSULTOR

        return "clientes/ver";
    }   


    @GetMapping("/lista")    
    public String clientesVer() {
       
         //ENTRA ACA SOLO SI ES CONSULTOR

        return "clientes/lista";
    } 



//ESTE ATRIBUTO SE DIFUNDE A TODAS LAS VISTAS QUE PERTENECEN AL CONTROLADOR, 
//ES UN ATRIBUTO GENERAL (COMO NOMBRE DE USUARIO LOGUEADO)
    @ModelAttribute("tituloEjemplo")
    public String cargarTituloEjemplo()
    {
        return "manejo de Errores";
    }



}
