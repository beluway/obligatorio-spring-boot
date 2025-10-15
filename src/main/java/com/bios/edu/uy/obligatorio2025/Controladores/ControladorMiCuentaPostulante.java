package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulantes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;


@Controller
@RequestMapping("/micuentaP")
public class ControladorMiCuentaPostulante {
    


@Autowired
private IServicioPostulantes servicioPostulantes;


    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) throws Exception {       

        Postulante postulante= servicioPostulantes.obtener(usuarioLogueado.getName());

        postulante.setClave("");        

        modelo.addAttribute("usuarioLogueado", postulante);

        return "/micuentaP/ver";
    }
    

    @PostMapping("/ver")
    public String postMethodName() {
     
        return "/micuentaP/ver";
    }
        

}