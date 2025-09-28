package com.bios.edu.uy.obligatorio2025.Controladores;

import java.lang.foreign.Arena;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.bios.edu.uy.obligatorio2025.Dominio.areas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioAreas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioAreas;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/areas")
public class ControladorAreas {

    @Autowired
    private IServicioAreas servicioAreas;

    @Autowired
    private IRepositorioAreas repositorioAreas;

    @GetMapping("/crear")
    public String crear (@ModelAttribute areas area, Model modelo) throws Exception
    {
        
        return "areas/crear";
    }


    @PostMapping("/crear") 
    public String procesarCrear(@ModelAttribute @Valid areas area, Model modelo, BindingResult resultado) throws Exception {
        areas existente = repositorioAreas.findByNombre(area.getNombre());
        String mensaje = "Se agregregó el área correctamente";

    if (existente != null) {
    throw new Exception("Ya existe el área con nombre: " + existente.getNombre());
    } else {
        modelo.addAttribute("mensaje",mensaje);
        repositorioAreas.save(area);
    }
        return "redirect:/areas/crear";

    }
    
    
}
