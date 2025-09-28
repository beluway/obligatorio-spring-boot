package com.bios.edu.uy.obligatorio2025.Controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.bios.edu.uy.obligatorio2025.Dominio.Area;
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
    
    @Autowired
    private IServicioAreas servicioAreas;

    @GetMapping("/crear")
    public String crear (@ModelAttribute Area area, HttpSession sesion, Model modelo)throws Exception
    {
        
        return "areas/crear";
    }

    @GetMapping("/lista")
    public String listarAreas(@ModelAttribute Area area, HttpSession sesion, Model modelo)throws Exception
    {
        List<Area> areas = servicioAreas.listaAreas();
        modelo.addAttribute("areas", areas);
        return "areas/lista";
    }

    @PostMapping("/crear") 
    public String procesarCrear(@ModelAttribute @Valid Area area, Model modelo, BindingResult resultado) throws Exception {
        Area existente = repositorioAreas.findByNombre(area.getNombre());
        String mensaje = "Se agregregó el área correctamente";

    if (existente != null) {
    throw new Exception("Ya existe el área con nombre: " + existente.getNombre());
    } else {
        modelo.addAttribute("mensaje",mensaje);
        repositorioAreas.save(area);
    }
        return "redirect:/areas/lista";

    }

    @GetMapping("/modificar")
    public String modificarArea(HttpSession sesion, Model modelo, @RequestParam Integer id)throws Exception
    {
        Area areaEncontrada = servicioAreas.obtener(id);

        if (areaEncontrada==null) {
            throw new Exception("No se encontró el área con ID: "+id);
        }

        modelo.addAttribute("area", areaEncontrada);

        return "areas/modificar";
    }

    @PostMapping("/modificar")
    public String procesarModificar(@ModelAttribute @Valid Area area) throws Exception {
    
    // Aquí implementas la lógica de validación si es necesaria.
    Area areaEncontrada = servicioAreas.obtener(area.getId());

        if (areaEncontrada==null) {
            throw new Exception("No se encontró el área.");
        }
    // Asumo que tu servicioAreas.modificar recibe el objeto con el ID y los nuevos datos.
    servicioAreas.modificar(area); 
    
    // Redirige al listado después de una modificación exitosa
   return "redirect:/areas/lista";
}
    
}
