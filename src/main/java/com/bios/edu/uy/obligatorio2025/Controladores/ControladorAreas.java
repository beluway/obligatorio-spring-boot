package com.bios.edu.uy.obligatorio2025.Controladores;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioAreas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioConsultores;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/areas")
public class ControladorAreas {
 
    @Autowired
    private IServicioAreas servicioAreas;

    @Autowired
    private IServicioConsultores servicioConsultores;

    //CREAR ÁREA GET
    @GetMapping("/crear")
    public String crear (Principal usuarioLogueado, Model modelo)throws Exception
    {
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("area",new Area());
        return "areas/crear";
    }


    
//CREAR ÁREA POST
    @PostMapping("/crear") 
    public String procesarCrear(@ModelAttribute @Valid Area area, BindingResult resultado, Model modelo, 
     RedirectAttributes attributes) throws Exception {
       
        if (resultado.hasErrors()) {
            return "areas/crear";
        }

    try {
        servicioAreas.agregar(area);
        attributes.addFlashAttribute("mensaje", "Se agregó el área correctamente");
        return "redirect:/areas/lista";
    } catch (Exception e) {
        modelo.addAttribute("mensaje", "Error, " + e.getMessage());
        return "areas/crear";
    }
}



    //LISTAR ÁREAS GET
    @GetMapping("/lista")
    public String listarAreas(String criterio, @ModelAttribute Area area, Principal usuarioLogueado, Model modelo)throws Exception
    {
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));

        List<Area> areas = servicioAreas.listaAreas();

        if(criterio!=null&&!criterio.isEmpty())
        {
            areas = servicioAreas.buscarPorCriterio(criterio);
        }
        else
        {
            areas = servicioAreas.listaAreas();
        }

        modelo.addAttribute("areas", areas);
        return "areas/lista";
    }

    //MODIFICAR ÁREA GET
    @GetMapping("/modificar")
    public String modificarArea(Area area, Model modelo, Principal usuarioLogueado) throws Exception
    {
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));


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
    public String eliminarArea(@RequestParam("id") Integer id, Model modelo, Principal usuarioLogueado) throws Exception
    {
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));

        Area areaEncontrada = servicioAreas.obtener(id);

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
    public String procesarEliminarArea(@RequestParam("id") Integer id, Model modelo, RedirectAttributes attributes) throws Exception{

        try{

            Area area = servicioAreas.obtener(id);

            if (area == null) {

                modelo.addAttribute("error", "El área no existe.");
                 modelo.addAttribute("area",area);
                return "areas/eliminar";
            }

            if(area.getAsignada()==true)
            
            {   
                modelo.addAttribute("error", "No es posible borrar un área ya asignada.");
                modelo.addAttribute("area",area);
                return "areas/eliminar";

            

            }
            else{
                servicioAreas.eliminar(area);

            attributes.addFlashAttribute("mensaje","Área eliminada con éxito.");
            return "redirect:/areas/lista";
                
            
            }
            
        }catch(Exception e){
            modelo.addAttribute("mensaje","Hubo un error "+ e.getMessage());
            return "areas/lista";
        }
    }


    
    
}
