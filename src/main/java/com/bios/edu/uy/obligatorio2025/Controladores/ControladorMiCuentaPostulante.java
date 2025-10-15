package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
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

        modelo.addAttribute("usuarioLogueado", postulante);

        return "/micuentaP/ver";
    }
    

    @PostMapping("/ver")
    public String modificarPostulante(
            @ModelAttribute @Valid Postulante postulante,
            BindingResult resultado,
            Model modelo,
            RedirectAttributes atributos) throws Exception {

        if (resultado.hasErrors()) {
            modelo.addAttribute("usuarioLogueado", postulante);
            return "micuentaP/ver"; // queda en la misma página si hay errores
        }

        // Codificar clave
        postulante.setActivo(true);
        postulante.setClave(codificador.encode(postulante.getClave()));
        servicioPostulantes.modificar(postulante);

        // Recargar los datos actualizados
        modelo.addAttribute("usuarioLogueado", postulante);
        atributos.addFlashAttribute("mensaje", "Datos modificados correctamente");

        return "redirect:/micuentaP/ver"; 

     }
        



}