package com.bios.edu.uy.obligatorio2025.Controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Servicios.ServicioConsultores;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/consultores")
public class ControladorConsultores {
 
@Autowired
private ServicioConsultores servicioConsultores;

  @GetMapping("/main")
    public String consultorCrear()
    {
    
        return "consultores/main";
        
    }

    @GetMapping("/crear")
    public String consultorCrear(Model modelo, HttpSession sesion) throws Exception
    {
        modelo.addAttribute("consultor", new Consultor());
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "consultores/crear";
        
    }
 


    @PostMapping("/crear")
    public String consultorCrear (@ModelAttribute @Valid Consultor consultor,
    Model modelo, 
    BindingResult resultado,
    RedirectAttributes atributos ) throws Exception
    {           

        if(resultado.hasErrors())
        {
            modelo.addAttribute("consultor", consultor);
            return "consultores/crear";
        }

        if(servicioConsultores.obtener(consultor.getUsuario())!=null)
        {
            modelo.addAttribute("mensaje", "ya existe el consultor");

            return "consultores/crear";
        }

        try
        {
            servicioConsultores.agregar(consultor);

            atributos.addFlashAttribute("mensaje","consultor agregado con exito");

            return "redirect:/consultores/crear";
        }

        catch(Exception ex)
        {
             modelo.addAttribute("mensaje","Hubo un error "+ e.getMessage());
           return "consultores/crear";

        }

       /*  Consultor existente = servicioConsultores.obtener(consultor.getUsuario());

          String mensaje = "Se agregregó el consultor correctamente";

        if(existente !=null)
        {
             throw new Exception("Ya existe el consultor con el usuario: " + existente.getUsuario());

        }
        else
        {
            modelo.addAttribute("mensaje",mensaje);
            servicioConsultores.agregar(consultor);
        }
        return "redirect:/consultores/crear"; */
    }


    @GetMapping("/eliminar")

    public String consultorEliminar(@ModelAttribute Consultor consultor) {
      
        return "consultores/eliminar";

    }

    @PostMapping("/eliminar")
    public String consultorEliminar(@ModelAttribute @Valid Consultor consultor, BindingResult resultado, Model modelo)  {
              
        return "redirect:/consultores/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String consultorModificar(Model modelo, HttpSession sesion) {
      
        modelo.addAttribute("consultor", new Consultor());
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "consultores/modificar";
    }
    
    @PostMapping("/modificar")
    public String consultorModificar(@ModelAttribute @Valid Consultor consultor,
    BindingResult resultado,
    Model modelo) {
       
     

        return "redirect:/consultores/modificar";
    }
    

    @GetMapping("/ver")    
    public String consultorVer(Model modelo, HttpSession sesion) {
       
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "consultores/ver";
    }   

    @GetMapping("/lista")
    public String consultoresLista(@ModelAttribute Consultor consultor, Model modelo, HttpSession sesion){

        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        List<Consultor> consultores = servicioConsultores.listaConsultores();
        modelo.addAttribute("consultores", consultores);

        return "consultores/lista";
    }

}
