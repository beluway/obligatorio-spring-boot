package com.bios.edu.uy.obligatorio2025.Controladores;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioConsultores;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/consultores")
public class ControladorConsultores {
 
@Autowired
private IServicioConsultores servicioConsultores;

  @GetMapping("/main")
    public String consultorCrear()
    {
    
        return "consultores/main";
        
    }

    @GetMapping("/crear")
    public String consultorCrear(Model modelo, Principal usuarioLogueado) throws Exception
    {
        modelo.addAttribute("consultor", new Consultor());
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));
        return "consultores/crear";
        
    }
 


    @PostMapping("/crear")
    public String consultorCrear (@ModelAttribute @Valid Consultor consultor,
    BindingResult resultado,
    Model modelo, 
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
            consultor.setActivo(true);

            servicioConsultores.agregar(consultor);

            atributos.addFlashAttribute("mensaje","consultor agregado con exito");

            return "redirect:/consultores/crear";
        }

        catch(Exception ex)
        {
             modelo.addAttribute("mensaje","Hubo un error "+ ex.getMessage());
           return "consultores/crear";

        }

    }


    @GetMapping("/eliminar")

    public String consultorEliminar(@ModelAttribute Consultor consultor, Principal usuarioLogueado, Model modelo) throws Exception {

        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));
      
        return "consultores/eliminar";

    }

    @PostMapping("/eliminar")
    public String consultorEliminar(@ModelAttribute @Valid Consultor consultor, BindingResult resultado, Model modelo) throws Exception {
              
        return "redirect:/consultores/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String consultorModificar(Model modelo, Principal usuarioLogueado) throws Exception{
      
        modelo.addAttribute("consultor", new Consultor());
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));
        return "consultores/modificar";
    }
    
    @PostMapping("/modificar")
    public String consultorModificar(@ModelAttribute @Valid Consultor consultor,
    BindingResult resultado,
    Model modelo) {
       
     

        return "redirect:/consultores/modificar";
    }
    

    @GetMapping("/ver")    
    public String consultorVer(@RequestParam String usuario, Model modelo, Principal usuarioLogueado) throws Exception{
       
        Consultor consultor = servicioConsultores.obtener(usuario);
        modelo.addAttribute("consultor", consultor);

        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));
        return "consultores/ver";
    }   




    
    @GetMapping("/lista")
    public String consultoresLista(String criterio,@ModelAttribute Consultor consultor, Model modelo, Principal usuarioLogueado)throws Exception{

         List<Consultor> consultores = servicioConsultores.listaConsultores();


            if (criterio != null && !criterio.isEmpty()) {
                consultores = servicioConsultores.buscarPorCriterio(criterio);
            }
            else {
                consultores = servicioConsultores.listaConsultores();
            }


        
        //SE SACA LA LISTA DE POSTULANTES DE LA BD 
        modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));

        
        modelo.addAttribute("consultores", consultores);

        return "consultores/lista";
    }

}
