package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.bios.edu.uy.obligatorio2025.Dominio.Area;

import com.bios.edu.uy.obligatorio2025.Servicios.IServicioAreas;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/areas")
public class ControladorAreas {
 
    
    @Autowired
    private IServicioAreas servicioAreas;

    @GetMapping("/crear")
    public String crear (@ModelAttribute Area area, HttpSession sesion, Model modelo)throws Exception
    {
        
        return "areas/crear";
    }


    @PostMapping("/crear") 
    public String procesarCrear(@ModelAttribute @Valid Area area, Model modelo, BindingResult resultado) throws Exception {

        Area existente = servicioAreas.obtener(area.getId());

    // Area existente = repositorioAreas.findByNombre(area.getNombre());
        String mensaje = "Se agregregó el área correctamente";

    if (existente != null) {
    throw new Exception("Ya existe el área con nombre: " + existente.getNombre());
    } else {

        //VER SI SE AGREGA area O existente

        modelo.addAttribute("mensaje",mensaje);
        servicioAreas.agregar(area);
    }
        return "redirect:/areas/crear";

    }
    
    
}
