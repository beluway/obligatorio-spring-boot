package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;

import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;

import ch.qos.logback.core.model.Model;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/clientes")

public class ControladorClientes {
/*    
    @Autowired
    private IRepostorioClientes repositorioClientes; */

    @Autowired
    private IServicioClientes servicioClientes;


    @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute Cliente cliente, Model modelo) throws Exception
    {
        
        
       
    
        return "clientes/crear";
        
    }

    //ACA VA FLASH ATTRIBUTES Y REDIRECT 
    @PostMapping("/crear")
    public String clienteProcesarCrear (@ModelAttribute @Valid Cliente cliente, Model modelo, BindingResult resultado) throws Exception 
    {              
        servicioClientes.agregar(cliente);
         
        return "redirect:/clientes/crear";
    }


    @GetMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute Cliente cliente) {
      
         //ENTRA ACA SOLO SI ES CONSULTOR

        return "clientes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Valid Cliente cliente, Model modelo, BindingResult resultado)  {
              
        return "redirect:/clientes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(@ModelAttribute Cliente cliente) {
      
         //ENTRA ACA SOLO SI ES CONSULTOR

        return "clientes/modificar";
    }
    
    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Valid Cliente cliente, Model modelo, BindingResult resultado) {
       
        return "redirect:/clientes/modificar";
    }
    

    @GetMapping("/ver")    
    public String clientesVer(@ModelAttribute Cliente cliente) {
       
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
