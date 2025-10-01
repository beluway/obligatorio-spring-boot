package com.bios.edu.uy.obligatorio2025.Controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;


import jakarta.servlet.http.HttpSession;
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
    public String clienteCrear(Model modelo, HttpSession sesion) throws Exception
    {        

        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "clientes/crear";        
    }

    //ACA VA FLASH ATTRIBUTES Y REDIRECT 
    @PostMapping("/crear")
    public String clienteProcesarCrear (@ModelAttribute @Valid Cliente cliente, 
    Model modelo, 
    BindingResult resultado,
    RedirectAttributes atributos) throws Exception 
    {              
         if(resultado.hasErrors()){
            atributos.addFlashAttribute("mensaje", "Errores en el formulario");
            return "redirect:/postulantes/crear";
          }

        servicioClientes.agregar(cliente);         
        
        return "redirect:/clientes/crear";
    }


    @GetMapping("/eliminar")
    public String clienteEliminar(Model modelo, HttpSession sesion) {
      
         //ENTRA ACA SOLO SI ES CONSULTOR
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "clientes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Valid Cliente cliente, Model modelo, BindingResult resultado)  {
              
        return "redirect:/clientes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(Model modelo, HttpSession sesion) {
      
         //ENTRA ACA SOLO SI ES CONSULTOR
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "clientes/modificar";
    }
    
    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Valid Cliente cliente, Model modelo, BindingResult resultado) {
       
        return "redirect:/clientes/modificar";
    }
    

    @GetMapping("/ver")    
    public String clienteVer(@RequestParam String usuario, Model modelo, HttpSession sesion) throws Exception {
       
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        Cliente cliente = servicioClientes.obtener(usuario);
        modelo.addAttribute("cliente", cliente);
        return "clientes/ver";
    }    


    @GetMapping("/lista")    
    public String clientesListar(@ModelAttribute Cliente clientes, Model modelo, HttpSession sesion) throws Exception {
       
        if (sesion.getAttribute("usuarioLogueado") instanceof Consultor) {
         //ENTRA ACA SOLO SI ES CONSULTOR
        List<Cliente> listaClientes = servicioClientes.listaClientes();

        modelo.addAttribute("clientes", listaClientes);
        modelo.addAttribute("usuarioLogueado", (Consultor)sesion.getAttribute("usuarioLogueado"));
        

        return "clientes/lista";
    }
    return "home/main";
    } 



//ESTE ATRIBUTO SE DIFUNDE A TODAS LAS VISTAS QUE PERTENECEN AL CONTROLADOR, 
//ES UN ATRIBUTO GENERAL (COMO NOMBRE DE USUARIO LOGUEADO)
    @ModelAttribute("tituloEjemplo")
    public String cargarTituloEjemplo()
    {
        return "manejo de Errores";
    }



}
