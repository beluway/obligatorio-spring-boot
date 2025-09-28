package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.bios.edu.uy.obligatorio2025.Dominio.Areas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioAreas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioAreas;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/areas")
public class ControladorAreas {

    @Autowired
    private IRepositorioAreas repositorioAreas;
    
    


    @GetMapping("/crear")
    public String crear (@ModelAttribute Areas area, HttpSession sesion, Model modelo)
    {
        Areas areaExistente = repositorioAreas.findById(area.getNombre()).orElse(null);

          

        repositorioAreas.save(area);

        //ENTRA ACA SOLO SI ES CONSULTOR
        return "areas/crear";
    }   


    @PostMapping("/crear") 
    public String crear(@ModelAttribute @Valid Areas area, Model modelo, BindingResult resultado) {
        
        return "redirect:/areas/crear";

    }
    
    
}
