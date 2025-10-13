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
        modelo.addAttribute("cliente", new Cliente());
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "clientes/crear";        
    }

    //ACA VA FLASH ATTRIBUTES Y REDIRECT 
    @PostMapping("/crear")
    public String clienteProcesarCrear (@ModelAttribute @Valid Cliente cliente, 
    BindingResult resultado,
    Model modelo,   
    RedirectAttributes attributes) throws Exception 
    {              
         if(resultado.hasErrors()){

            modelo.addAttribute("cliente", cliente);
            return "clientes/crear";
          }

          if(servicioClientes.obtener(cliente.getUsuario())!=null)
          {
             modelo.addAttribute("mensaje", "Ya existe el usuario");
             return "clientes/crear";
          }

          try
          {

            cliente.setActivo(true);

            servicioClientes.agregar(cliente); 

            attributes.addFlashAttribute("mensaje","Cliente agregado con éxito.");
            return "redirect:/clientes/lista";
          
          }
          catch(Exception e)
          {
            modelo.addAttribute("mensaje","Hubo un error "+ e.getMessage());
           return "clientes/crear";
          }                     
        
    }


    @GetMapping("/eliminar")
    public String clienteEliminar(Model modelo, HttpSession sesion) {
      
     
        //ENTRA ACA SOLO SI ES CONSULTOR
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "clientes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Valid Cliente cliente, Model modelo, BindingResult resultado, RedirectAttributes attributes) throws Exception  {
              
        if(resultado.hasErrors())
        {
            return "clientes/eliminar";
        }

        try
        {
            servicioClientes.eliminar(cliente);
            attributes.addFlashAttribute("mensaje","Cliente eliminado con éxito");
            return "redirect:/clientes/lista";
        }
        catch(Exception ex)
        {
            modelo.addAttribute("mensaje", "Hubo un error "+ex.getMessage());

            return "clientes/eliminar";
        }
    }    
    

    @GetMapping("/modificar")
    public String clienteModificar(@RequestParam String usuario,Model modelo, HttpSession sesion) throws Exception {

         //ENTRA ACA SOLO SI ES CONSULTOR
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        Cliente cliente = servicioClientes.obtener(usuario);

        if (cliente==null) {
             modelo.addAttribute("mensaje", "Cliente no encontrado");
            return "redirect:/clientes/lista";
        }

        modelo.addAttribute("cliente", cliente);

        return "clientes/modificar";
    }
    

    @PostMapping("/modificar")
    public String procesarModificar(@ModelAttribute /*@Valid*/ Cliente cliente, 
    @RequestParam(required = false)String clave,
     BindingResult resultado,
     RedirectAttributes attributes, Model modelo) throws Exception{
     
        if(resultado.hasErrors())
        {
            modelo.addAttribute("cliente", new Cliente());
            return "clientes/modificar";
        }

        try
        {
        // Si se ingresó una nueva clave, la reemplaza
        if (clave != null && !clave.isBlank()) {
            cliente.setClave(clave);
        }
        servicioClientes.modificar(cliente,clave);
        attributes.addFlashAttribute("exito", "Cliente modificado correctamente");

             return "redirect:/clientes/lista";
        }

        catch (Exception ex)
        {
           return "clientes/modificar";
        }
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
