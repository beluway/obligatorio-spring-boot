package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String modificarCliente(
            @ModelAttribute @Valid Cliente cliente,
            BindingResult resultado,
            Model modelo,
            RedirectAttributes atributos) throws Exception {

            /*if (resultado.hasErrors()) {
            modelo.addAttribute("usuarioLogueado", cliente);
            return "micuentaC/ver"; // queda en la misma página si hay errores
            } */


         if(resultado.hasErrors()){
             return "micuentaC/ver";
          }


        // Codificar clave
        cliente.setActivo(true);
        cliente.setClave(codificador.encode(cliente.getClave()));
        servicioClientes.modificar(cliente);

        // Recargar los datos actualizados
        modelo.addAttribute("usuarioLogueado", cliente);
        atributos.addFlashAttribute("mensaje", "Datos modificados correctamente");

        return "redirect:/micuentaC/ver"; 

     }
        

}