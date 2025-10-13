package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.web.exchanges.HttpExchange.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion.PostulacionId;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
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

    @Autowired
    private IServicioPostulantes servicioPostulantes;



    @GetMapping("/crear")
    public String crear(Model modelo, HttpSession sesion, Principal usuarioLogueado) throws Exception
    {    
        
     Postulacion postulacion = new Postulacion();
     postulacion.setOferta(new Oferta());   

      
    Postulante postulanteLogueado = servicioPostulantes.obtener(usuarioLogueado.getName());  
             
            //SI EL POSTULANTE TIENE 3 POSSTULACIONES ACTIVAS, NO SE PUEDE GUARDAR OTRA
            if(postulanteLogueado.getCantidadPostulaciones()==3)
            {
                return "redirect:/home/main";
            } 


      //  modelo.addAttribute("usuarioLogueado", postulanteLogueado); 
       modelo.addAttribute("postulacion", new Postulacion());       
       modelo.addAttribute("ofertasVigentesParaPostularse", servicioPostulaciones.listaOfertasVigentesParaPostularse((Postulante)usuarioLogueado));

        return "postulaciones/crear";    
     
    }

 

    @PostMapping("/crear")
    public String crear (
    @ModelAttribute @Valid Postulacion postulacion, 
    BindingResult resultado, 
    Model modelo, 
    HttpSession sesion,
    RedirectAttributes attributes, Principal usuarioLogueado) throws Exception /*  */
    {      
                
      //  Postulante postulanteLogueado = (Postulante)sesion.getAttribute("usuarioLogueado");

        Postulante postulanteLogueado=servicioPostulantes.obtener(usuarioLogueado.getName());  
/* 
        if(usuarioLogueado instanceof Postulante)
        { */


             
        Oferta ofertaEncontrada = servicioOfertas.obtener(postulacion.getOferta().getId()); 
       

        //************PARTE CLAVE COMPUESTA  */
        /// AHORA PARA LA CLAVE COMPUESTA            
        PostulacionId claveCompuestaPostulacion = new PostulacionId();

        //SE VINCULA LA OFERTA DE LA CLAVE COMPUESTA CON LA OFERTA ELEGIDA
        claveCompuestaPostulacion.setIdOferta(postulacion.getOferta().getId());

        //SE VINCULA EL USUARIO DE LA CLAVE COMPUESTA CON EL USUARIO LOGUEADO (POSTULANTE)
        claveCompuestaPostulacion.setUsuarioPostulante(postulanteLogueado.getUsuario());

        //Y SE ASIGNA LA CLAVE COMPUESTA CON LOS 2 OBJETOS ADENTRO A POSTULACION        
        postulacion.setId(claveCompuestaPostulacion);


        //******PARTE ATRIBUTOS DE OBJETO POSTULACION */

        postulacion.setOferta(ofertaEncontrada);
        postulacion.setPostulante(postulanteLogueado);
        postulacion.setFechaPostulacion(LocalDate.now());
                
     /*    } */


        try
        {   
        
               //DESPUES QUE SE POSTULA A UNA OFERTA, SE CUENTA +1, HASTA QUE SEAN 3 RESERVAS ACTUALES.
                postulanteLogueado.setCantidadPostulaciones(postulanteLogueado.getCantidadPostulaciones()+1);
                       
                servicioPostulaciones.agregar(postulacion);

                String mensaje = "Se agregó la postulación correctamente";
                attributes.addFlashAttribute("mensaje",mensaje);               
             

                return "redirect:/postulaciones/lista"; // redirige al listado después de crear
            
        }
        
        catch(Exception ex)
        {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "postulaciones/crear";
        }        

    }



@GetMapping("/eliminar")
    public String eliminar(Model modelo, HttpSession sesion,
    @RequestParam("codigoOferta")Integer codigoOferta, 
    @RequestParam("codigoPostulante")String codigoPostulante) throws Exception
    {  
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado")); 
        
        Optional<Postulacion> encontrada= servicioPostulaciones.obtener(codigoOferta,codigoPostulante);

        if(!encontrada.isEmpty())
        {
            modelo.addAttribute("postulacion", encontrada.get());

             return "postulaciones/eliminar"; 
        }


        return "redirect:/postulaciones/lista";        
    }



   
    @PostMapping("/eliminar")
    public String eliminar (@ModelAttribute @Valid Postulacion postulacion,BindingResult resultado, Model modelo, RedirectAttributes attributes,  
     @RequestParam("codigoOferta")Integer codigoOferta, 
    @RequestParam("codigoPostulante")String codigoPostulante, HttpSession sesion) throws Exception 
    {         
        if(resultado.hasErrors())
        {
            return "postulaciones/eliminar";
        }  

        try
        {
            Optional<Postulacion> encontrada = servicioPostulaciones.obtener(codigoOferta,codigoPostulante);
            /* Optional<Postulacion> postulacionExistente = servicioPostulaciones.findByOfertaAndPostulante(postulacion.getOferta(),postulacion.getPostulante());   */
            
            if (encontrada==null) {
                throw new ExcepcionNoExiste("La postulación con a la Oferta "+postulacion.getOferta().getId()+"del Postulante "+
                postulacion.getPostulante().getUsuario()+"no fue encontrada.");
            }
            servicioPostulaciones.eliminar(encontrada.get());

            Postulante postulanteParaActualizacionCantidadPostulaciones = (Postulante)sesion.getAttribute("usuarioLogueado");

            int cantidadNuevaPostulaciones = postulanteParaActualizacionCantidadPostulaciones.getCantidadPostulaciones()-1;

            postulanteParaActualizacionCantidadPostulaciones.setCantidadPostulaciones(cantidadNuevaPostulaciones);


            attributes.addFlashAttribute("mensaje","Postulación eliminada con éxito.");
            return "redirect:/postulaciones/lista";

        } catch (Exception ex) {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "postulaciones/eliminar";
        }                
    }




     @GetMapping("/ver")
     public String ver(Model modelo, HttpSession sesion) throws Exception
     {
        return "postulaciones/ver";     
     }

     

    @GetMapping("/lista")
    public String lista(Model modelo, Principal usuarioLogueado) throws Exception
    {    
      Postulante postulanteLogueado = servicioPostulantes.obtener(usuarioLogueado.getName());          


        modelo.addAttribute("postulacionesPostulante",  servicioPostulaciones.listaPostulacionesPorPostulante(postulanteLogueado));

        return "postulaciones/lista";        
    }


}
