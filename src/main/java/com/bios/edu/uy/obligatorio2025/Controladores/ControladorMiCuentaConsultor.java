package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioConsultores;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/micuentaConsu")
public class ControladorMiCuentaConsultor {
      
    @Autowired
  private IServicioConsultores servicioConsultores;

  @Autowired
  PasswordEncoder codificador; 


    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) throws Exception {
       
        Consultor consultor= servicioConsultores.obtener(usuarioLogueado.getName());

        consultor.setClave("");        

        modelo.addAttribute("consultor", consultor);

        return "/micuentaConsu/ver";

    }
           

    @PostMapping("/ver") 
   public String procesarModificar(@ModelAttribute @Valid Consultor consultor, 
    @RequestParam(required = false)String nuevaClave,
     BindingResult resultado,
     RedirectAttributes attributes, Model modelo, @RequestParam String usuario) throws Exception{
    Consultor consultorExiste = servicioConsultores.obtener(usuario);

        if (consultorExiste == null){
            modelo.addAttribute("mensaje", "consultor no encontrado");
            return "micuentaConsu/ver";
        }

        if(resultado.hasErrors())
        {
            modelo.addAttribute("consultor", consultor);
            modelo.addAttribute("mensaje", "Corrija los errores");
            return "micuentaConsu/ver";
        }

        try
        {
        // Si se ingresó una nueva clave, la reemplaza
       /*  if (nuevaClave != null && !nuevaClave.isBlank()) {
            consultorExiste.setClave(nuevaClave);
        } */
        // llama al servicio que maneja clave opcional
        servicioConsultores.modificar(consultor);

        attributes.addFlashAttribute("exito", "Consultor modificado correctamente");

             return "redirect:/micuentaConsu/ver";
        }

        catch (Exception ex)
        {
           return "micuentaConsu/ver";
        }
    }
        


    
      @PostMapping("/eliminar")
      public String eliminar (Model modelo, RedirectAttributes attributes,  
       @RequestParam("usuario")String usuario, 
       Principal usuarioLogueado) throws Exception 
      {         
       
         Consultor consultorExiste = servicioConsultores.obtener(usuario);
            
            if (consultorExiste==null) {

                throw new ExcepcionNoExiste("El consultor no se encontró");              
            } 

            try
            {
           
              servicioConsultores.eliminar(consultorExiste.getUsuario());
              return "redirect:/home/index";

            } 
            catch (Exception ex) {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "micuentaConsu/ver";
            }                
      }


}

