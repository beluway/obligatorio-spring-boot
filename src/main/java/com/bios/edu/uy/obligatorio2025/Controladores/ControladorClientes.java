package com.bios.edu.uy.obligatorio2025.Controladores;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Usuario.Crear;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/clientes")

public class ControladorClientes {

    //private final Servicios.ServicioOfertas servicioOfertas;

    @Autowired
    private final IServicioClientes servicioClientes;

    private final IServicioOfertas servicioOfertas;

    ControladorClientes(/*Servicios.*/IServicioOfertas servicioOfertas, IServicioClientes servicioClientes) {
        this.servicioOfertas = servicioOfertas;
        this.servicioClientes = servicioClientes;
    }

    @GetMapping("/crear")
    public String clienteCrear(Model modelo, Principal usuarioLogueado) throws Exception
    {        
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("cliente", new Cliente());
        return "clientes/crear";        
    }

    //ACA VA FLASH ATTRIBUTES Y REDIRECT 
    @PostMapping("/crear")
    public String clienteProcesarCrear (@ModelAttribute @Validated(Crear.class) Cliente cliente, 
    BindingResult resultado,
    Model modelo,   
    RedirectAttributes attributes) throws Exception 
    {              
         if(resultado.hasErrors()){

            modelo.addAttribute("cliente", cliente);
            return "clientes/crear";
          }

          // 🔹 Verificar si ya existe la URL
    if (servicioClientes.existePorUrl(cliente.getUrl())) {
        resultado.rejectValue("url", "error.url", "Ya existe un cliente con esa URL.");
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
    public String clienteEliminar(@RequestParam String usuario,Model modelo, Principal usuarioLogueado) throws Exception {
     
        //ENTRA ACA SOLO SI ES CONSULTOR
        //modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));

    // Buscar el cliente
    Cliente cliente = servicioClientes.obtener(usuario);

    if (cliente == null) {
        modelo.addAttribute("mensaje", "Cliente no encontrado.");
        return "clientes/lista";
    }

    //carga la cantidad de ofertas que tiene ese cliente
    int cantidadOfertas = servicioOfertas.listaOfertasCliente(cliente).size();
    modelo.addAttribute("cantidadOfertas", cantidadOfertas);

    modelo.addAttribute("cliente", cliente);
    return "clientes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@RequestParam String usuario, 
    Model modelo, 
    RedirectAttributes attributes) throws Exception  
    {
              
    // Buscar el cliente
    Cliente clienteEncontrado = servicioClientes.obtener(usuario);

    if (clienteEncontrado == null) {
        attributes.addFlashAttribute("error", "Cliente no encontrado.");
        return "redirect:/clientes/lista";
    }

    try 
    {
      /*   // Si tiene ofertas → baja lógica
        if (!servicioOfertas.listaOfertasCliente(clienteEncontrado).isEmpty()) {
            clienteEncontrado.setActivo(false);
            servicioClientes.modificar(clienteEncontrado, null);
            attributes.addFlashAttribute("exito", "Cliente dado de baja (baja lógica) porque tiene ofertas publicadas.");
        } else {
            // Sin ofertas → eliminación definitiva
            
        } */

            servicioClientes.eliminar(clienteEncontrado);
            attributes.addFlashAttribute("exito", "Cliente eliminado.");
    }
     catch (Exception ex) {
        attributes.addFlashAttribute("error", "Hubo un error al eliminar el cliente: " + ex.getMessage());
    }

    return "redirect:/clientes/lista";
    }


       
    

    @GetMapping("/modificar")
    public String clienteModificar(@RequestParam String usuario,Model modelo, Principal usuarioLogueado) throws Exception {

         //ENTRA ACA SOLO SI ES CONSULTOR
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));

        Cliente cliente = servicioClientes.obtener(usuario);

        if (cliente==null) {
             modelo.addAttribute("mensaje", "Cliente no encontrado");
            return "redirect:/clientes/lista";
        }

        modelo.addAttribute("cliente", cliente);

        return "clientes/modificar";
    }
    

    @PostMapping("/modificar")
    public String procesarModificar(@ModelAttribute @Valid Cliente cliente, 
    @RequestParam(required = false)String nuevaClave,
     BindingResult resultado,
     RedirectAttributes attributes, Model modelo) throws Exception{
     
    Cliente clienteExistente = servicioClientes.obtener(cliente.getUsuario());

        if (clienteExistente == null){
            modelo.addAttribute("mensaje", "cliente no encontrado");
            return "clientes/modificar";
        }

        if(resultado.hasErrors())
        {
            modelo.addAttribute("cliente", cliente);
            modelo.addAttribute("mensaje", "Corrija los errores");
            return "clientes/modificar";
        }

        try
        {
        // Si se ingresó una nueva clave, la reemplaza
        /*if (nuevaClave != null && !nuevaClave.isBlank()) {
            clienteExistente.setClave(nuevaClave);
        }*/

        // llama al servicio que maneja clave opcional
        servicioClientes.modificar(cliente);

        attributes.addFlashAttribute("exito", "Cliente modificado correctamente");

             return "redirect:/clientes/lista";
        }

        catch (Exception ex)
        {
           return "clientes/modificar";
        }
    }
    

    @GetMapping("/ver")    
    public String clienteVer(@RequestParam String usuario, Model modelo, Principal usuarioLogueado) throws Exception {
       
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        Cliente cliente = servicioClientes.obtener(usuario);
        modelo.addAttribute("cliente", cliente);
        return "clientes/ver";
    }    


    @GetMapping("/lista")    
    public String clientesListar(@ModelAttribute Cliente clientes, Model modelo,Principal usuarioLogueado) throws Exception {
       
      
         //ENTRA ACA SOLO SI ES CONSULTOR
         //Muestro solo los clientes que están activos (no tiene baja lógica)
        List<Cliente> listaClientes = servicioClientes.listarActivos();
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("clientes", listaClientes);
        
        return "clientes/lista";
    
    
        // return "home/main";
    } 



//ESTE ATRIBUTO SE DIFUNDE A TODAS LAS VISTAS QUE PERTENECEN AL CONTROLADOR, 
//ES UN ATRIBUTO GENERAL (COMO NOMBRE DE USUARIO LOGUEADO)
    @ModelAttribute("tituloEjemplo")
    public String cargarTituloEjemplo()
    {
        return "manejo de Errores";
    }



}
