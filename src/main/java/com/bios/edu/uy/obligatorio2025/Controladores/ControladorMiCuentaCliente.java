package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;

import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/micuentaC")
public class ControladorMiCuentaCliente  {
    
    @Autowired
  private IServicioClientes servicioClientes;

  @Autowired
  PasswordEncoder codificador; 


    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) throws Exception {
       
        Cliente cliente= servicioClientes.obtener(usuarioLogueado.getName());

        cliente.setClave("");        

        modelo.addAttribute("usuarioLogueado", cliente);

        return "/micuentaC/ver";

    }
           
    

    @PostMapping("/ver") 
   public String procesarModificar(@ModelAttribute @Valid Cliente cliente, 
    @RequestParam(required = false)String nuevaClave,
     BindingResult resultado,
     RedirectAttributes attributes, Model modelo) throws Exception{
     
    Cliente clienteExistente = servicioClientes.obtener(cliente.getUsuario());

        if (clienteExistente == null){
            modelo.addAttribute("mensaje", "cliente no encontrado");
            return "micuentaC/ver";
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

             return "redirect:/micuentaC/ver";
        }

        catch (Exception ex)
        {
           return "micuentaC/ver";
        }
    }
        

}