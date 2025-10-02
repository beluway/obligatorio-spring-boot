package com.bios.edu.uy.obligatorio2025.Controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

    //CREAR ÁREA GET
    @GetMapping("/crear")
    public String crear (@ModelAttribute Area area, HttpSession sesion, Model modelo)throws Exception
    {
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        return "areas/crear";
    }

//CREAR ÁREA POST
    @PostMapping("/crear") 
    public String procesarCrear(@ModelAttribute @Valid Area area, Model modelo, BindingResult resultado,
     RedirectAttributes attributes) throws Exception {
       
        if (resultado.hasErrors()) {
            return "areas/crear";
        }

        try{

        servicioAreas.agregar(area);

        String mensaje = "Se agregó el área correctamente";

        attributes.addFlashAttribute("mensaje",mensaje);
    
        return "redirect:/areas/lista";
    }catch(Exception e){

        modelo.addAttribute("mensaje", "Error, "+e.getMessage());
        return "areas/crear";
    }

    }
    //LISTAR ÁREAS GET
        @GetMapping("/lista")
    public String listarAreas(@ModelAttribute Area area, HttpSession sesion, Model modelo)throws Exception
    {
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        List<Area> areas = servicioAreas.listaAreas();
        modelo.addAttribute("areas", areas);
        return "areas/lista";
    }

    //MODIFICAR ÁREA GET
    @GetMapping("/modificar")
    public String modificarArea(Area area, Model modelo, HttpSession sesion) throws Exception
    {
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));


        Area areaEncontrada = servicioAreas.obtener(area.getId());

        if (areaEncontrada!=null) {
            modelo.addAttribute("area", areaEncontrada);
        }
        else{
            modelo.addAttribute("mensaje", "El área no existe");
        }
        return "areas/modificar";
    }

    
//MODIFICAR ÁREA POST
    @PostMapping("/modificar")
    public String procesarModificarArea(@ModelAttribute @Valid Area area,BindingResult resultado, Model modelo, RedirectAttributes attributes) throws Exception{

        if (resultado.hasErrors()) {
            return "areas/modificar";
        }

        try{
            servicioAreas.modificar(area);

            attributes.addFlashAttribute("mensaje","Área modificada con éxito.");
            return "redirect:/areas/lista";
        }catch(Exception e){
            modelo.addAttribute("mensaje","Hubo un error "+ e.getMessage());
            return "areas/lista";
        }
    }

        //ELIMINAR ÁREA GET
    @GetMapping("/eliminar")
    public String eliminarArea(Area area, Model modelo, HttpSession sesion) throws Exception
    {
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        Area areaEncontrada = servicioAreas.obtener(area.getId());

        if (areaEncontrada!=null) {
            modelo.addAttribute("area", areaEncontrada);
        }
        else{
            modelo.addAttribute("mensaje", "El área no existe");
        }
        return "areas/eliminar";
    }
    
    //ELIMINAR ÁREA POST
    @PostMapping("/eliminar")
    public String procesarEliminarArea(@ModelAttribute @Valid Area area,BindingResult resultado, Model modelo, RedirectAttributes attributes) throws Exception{

        if (resultado.hasErrors()) {
            return "areas/eliminar";
        }

        try{
            servicioAreas.eliminar(area);

            attributes.addFlashAttribute("mensaje","Área eliminada con éxito.");
            return "redirect:/areas/lista";
        }catch(Exception e){
            modelo.addAttribute("mensaje","Hubo un error "+ e.getMessage());
            return "areas/lista";
        }
    }


    
    
}
