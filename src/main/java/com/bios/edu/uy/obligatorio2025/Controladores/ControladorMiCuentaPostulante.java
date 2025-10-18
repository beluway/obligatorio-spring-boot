package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulantes;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
@RequestMapping("/micuentaP")
public class ControladorMiCuentaPostulante {
    


  @Autowired
  private IServicioPostulantes servicioPostulantes;

  @Autowired
  PasswordEncoder codificador; 

    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) throws Exception {       

        Postulante postulante= servicioPostulantes.obtener(usuarioLogueado.getName());

        postulante.setClave("");        

        modelo.addAttribute("postulante", postulante);

        return "/micuentaP/ver";
    }
    

    @PostMapping("/ver")
    public String modificarPostulante(
            @ModelAttribute @Valid Postulante postulante,
            BindingResult resultado,
            Model modelo,
            RedirectAttributes atributos) throws Exception {

            if (resultado.hasErrors()) {
                modelo.addAttribute("postulante", postulante);
                return "micuentaP/ver"; // queda en la misma página si hay errores
            }


       // Postulante postulanteEnBD = servicioPostulantes.obtener(postulante.getUsuario());

        // Codificar clave
      /*   postulanteEnBD.setActivo(true);
        postulanteEnBD.setClave(codificador.encode(postulante.getClave())); */
        servicioPostulantes.modificar(postulante);

        // Recargar los datos actualizados
        modelo.addAttribute("postulante", postulante);
        atributos.addFlashAttribute("mensaje", "Datos modificados correctamente");

        return "redirect:/micuentaP/ver"; 

     }
        
/* 
      <input type="hidden" name="codigoOferta" th:value="*{id.idOferta}" />
                <input type="hidden" name="codigoPostulante" th:value="*{id.usuarioPostulante}" />      */

      @PostMapping("/eliminar")
      public String eliminar (Model modelo, RedirectAttributes attributes,  
       @RequestParam("codigoPostulante")String codigoPostulante, 
       Principal usuarioLogueado) throws Exception 
      {         
       
         Postulante encontrado = servicioPostulantes.obtener(codigoPostulante);
            /* Optional<Postulacion> postulacionExistente = servicioPostulaciones.findByOfertaAndPostulante(postulacion.getOferta(),postulacion.getPostulante());   */
            
            if (encontrado==null) {

                throw new ExcepcionNoExiste("El postulante no se encontró");              
            } 

            try
            {
           
              servicioPostulantes.eliminar(encontrado.getUsuario());        

              attributes.addFlashAttribute("mensaje","Postulación eliminada con éxito.");
              return "redirect:/home/index";

            } 
            catch (Exception ex) {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "micuentaP/ver";
            }                
      }


}