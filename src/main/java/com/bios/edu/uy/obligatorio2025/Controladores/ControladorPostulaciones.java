package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulaciones;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulantes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/postulaciones")
public class ControladorPostulaciones {
   
    @Autowired
    private IServicioPostulaciones servicioPostulaciones;

     @Autowired
    private IServicioOfertas servicioOfertas;

    @GetMapping("/crear")
    public String crear(Model modelo, HttpSession sesion)
    {    
         modelo.addAttribute("usuarioLogueado", (Postulante)sesion.getAttribute("usuarioLogueado"));   
         modelo.addAttribute("postulacion", new Postulacion());       
         modelo.addAttribute("ofertasVigentes", servicioOfertas.listaOfertasVigentes());

        return "postulaciones/crear";    
     
    }



    @PostMapping("/crear")
    public String crear (@ModelAttribute @Valid Postulacion postulacion, 
    Model modelo, 
    BindingResult 
    resultado, 
    HttpSession sesion,
    RedirectAttributes attributes) throws Exception 
    {       
        Integer cantidadOfertasVencidas;
        
        Postulante postulanteLogueado = (Postulante)sesion.getAttribute("usuarioLogueado");

        //CANTIDAD DE POSTULACIONES ACTUALES DEL USUARIO LOGUEADO
        Integer cantidadPostulacionesActuales = (postulanteLogueado).getCantidadPostulaciones();

        if(resultado.hasErrors())
        {
            return "postulaciones/crear";
        }

        try
        {   
            //CANTIDAD DE OFERTAS VENCIDAS DEL POSTULANTE            
            cantidadOfertasVencidas = servicioOfertas.cantidadOfertasVencidasPorUsuario((postulanteLogueado).getUsuario());
            
            //SI EL POSTULANTE TIENE 3 POSSTULACIONES ACTIVAS, NO SE PUEDE GUARDAR OTRA
            if(cantidadPostulacionesActuales - cantidadOfertasVencidas ==3)
            {
                return "redirect:/home/main";
            }

            else
            {
                servicioPostulaciones.agregar(postulacion);
 
                String mensaje = "Se agregó la postulación correctamente";
                attributes.addFlashAttribute("mensaje",mensaje);
                
                //DESPUES QUE SE POSTULA A UNA OFERTA, SE CUENTA +1, HASTA QUE SEAN 3 RESERVAS ACTUALES.
                postulanteLogueado.setCantidadPostulaciones(cantidadPostulacionesActuales++);
            }
        }
        catch(Exception ex)
        {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "postulaciones/crear";
        }        

        return "postulaciones/crear";
    }


     @GetMapping("/eliminar")
    public String eliminar(@ModelAttribute Postulacion postulacion)
    {    
        return "postulaciones/eliminar";        
    }


    @PostMapping("/eliminar")
    public String eliminar (@ModelAttribute @Valid Postulacion postulacion, Model modelo, BindingResult resultado,RedirectAttributes attributes) throws Exception 
    {         
        if(resultado.hasErrors())
        {
            return "postulaciones/eliminar";
        }

        try
        {
            servicioPostulaciones.eliminar(postulacion);                         
            attributes.addFlashAttribute("mensaje","Postulación eliminada con éxito.");
            return "redirect:/postulaciones/lista";

        } catch (Exception ex) {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "postulaciones/eliminar";
        }                
    }


     @GetMapping("/lista")
    public String ver(Model modelo, HttpSession sesion) throws Exception
    {    
        servicioPostulaciones.listaPostulacionesPorUsuario();

        return "postulaciones/lista";        
    }


}
