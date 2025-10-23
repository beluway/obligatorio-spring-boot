package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion.PostulacionId;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;

import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulaciones;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulantes;

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
    public String crear(Model modelo, Principal usuarioLogueado) throws Exception
    {    
        
     Postulacion postulacion = new Postulacion();
     postulacion.setOferta(new Oferta());         

     //SE OBTIENE EL POSTULANTE LOGUEADO
    Postulante postulanteLogueado = servicioPostulantes.obtener(usuarioLogueado.getName());  
                

        modelo.addAttribute("postulacion", new Postulacion());
        modelo.addAttribute("usuarioLogueado", postulanteLogueado);       
       modelo.addAttribute("ofertasVigentesParaPostularse", servicioPostulaciones.listaOfertasVigentesParaPostularse(/*(Postulante)usuarioLogueado*/postulanteLogueado));

        return "postulaciones/crear";    
     
    }

 

    @PostMapping("/crear")
    public String crear (
    @ModelAttribute @Valid Postulacion postulacion, 
    BindingResult resultado, 
    Model modelo,   
    RedirectAttributes attributes, Principal usuarioLogueado) throws Exception /*  */
    {      
                
     Postulante postulanteLogueado=servicioPostulantes.obtener(usuarioLogueado.getName());  


    //SE OBTIENEN TODAS LAS POSTULACIONES EL POSTULANTE
    List<Postulacion> listaPostulaciones = servicioPostulaciones.listaPostulacionesPorPostulante(postulanteLogueado);


    //**** ESTE METODDO LO QUE HACE ES DESCONTAR 1 SEGUN LA CANTIDAD DE POSTULACIONES VENCIDAS.*/
    //DE TODAS LAS POSTULACIONES
    for(Postulacion P : listaPostulaciones)
    {

        if(P.getOferta().getFechaCierre().isBefore(LocalDate.now()))
        {
           int cantidadPostulacionesActulizadasPorOfertasVencidas = P.getPostulante().getCantidadPostulaciones();
           P.getPostulante().setCantidadPostulaciones(cantidadPostulacionesActulizadasPorOfertasVencidas-1);


           //SE ACTUALIZA LA CANTIDAD DE POSTULACIONES
          servicioPostulantes.actualizarCantidad(usuarioLogueado.getName(), cantidadPostulacionesActulizadasPorOfertasVencidas);  
                     
      /*   servicioPostulaciones.eliminar(P); */
        }
    }


    //SI EL POSTULANTE LOGJUEADO TIENE 3 POSTULACIONES
    if(postulanteLogueado.getCantidadPostulaciones()==3)
    {

        /// mensajito que diga: "ya alcanzó las 3 postulaciones"
                return "redirect:/postulaciones/lista";
    }  


    //SI LA CANTIDAD DE POSTULACIONES DE MENOR A 3, SE POSTULA
    else
    {
                    
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
                
                servicioPostulaciones.agregar(postulacion);


               int cantidadPostulacionesActulizadasPorOfertasVencidas = postulanteLogueado.getCantidadPostulaciones()+1;


               //DESPUES QUE SE POSTULA A UNA OFERTA, SE CUENTA +1, HASTA QUE SEAN 3 RESERVAS ACTUALES.

                //postulanteLogueado.setCantidadPostulaciones(postulanteLogueado.getCantidadPostulaciones()+1);
                   
                //CORRESPONDE CON LA ACTUALIZACION DE LA CANTIDAD DE POSTULACIONES
             // servicioPostulantes.modificar(postulanteLogueado);



              servicioPostulantes.actualizarCantidad(postulanteLogueado.getUsuario(),cantidadPostulacionesActulizadasPorOfertasVencidas);

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

   
    }



@GetMapping("/eliminar")
    public String eliminar(Model modelo, Principal usuarioLogueado,
    @RequestParam("codigoOferta")Integer codigoOferta, 
    @RequestParam("codigoPostulante")String codigoPostulante) throws Exception
    {  
        modelo.addAttribute("usuarioLogueado", servicioPostulantes.obtener(usuarioLogueado.getName())); 
        
        Optional<Postulacion> encontrada= servicioPostulaciones.obtener(codigoOferta,codigoPostulante);

        if(!encontrada.isEmpty())
        {
            modelo.addAttribute("postulacion", encontrada.get());

             return "postulaciones/eliminar"; 
        }


        return "redirect:/postulaciones/lista";        
    }



   
    @PostMapping("/eliminar")
    public String eliminar (Model modelo, RedirectAttributes attributes,  
     @RequestParam("codigoOferta")Integer codigoOferta, 
    @RequestParam("codigoPostulante")String codigoPostulante, Principal usuarioLogueado) throws Exception 
    {         
       
         Optional<Postulacion> encontrada = servicioPostulaciones.obtener(codigoOferta,codigoPostulante);
            /* Optional<Postulacion> postulacionExistente = servicioPostulaciones.findByOfertaAndPostulante(postulacion.getOferta(),postulacion.getPostulante());   */
            
            if (!encontrada.isPresent()) {
                throw new ExcepcionNoExiste("La postulación no se encontró");
            } 

            try
            {
           

            servicioPostulaciones.eliminar(encontrada.get());

            Postulante postulanteParaActualizacionCantidadPostulaciones = servicioPostulantes.obtener(usuarioLogueado.getName());

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
     public String ver(Model modelo, Principal usuarioLogueado) throws Exception
     {
        modelo.addAttribute("usuarioLogueado", servicioPostulantes.obtener(usuarioLogueado.getName()));
        return "postulaciones/ver";     
     }

     



    @GetMapping("/lista")
    public String lista(String criterio, 
    Model modelo, 
    Principal usuarioLogueado) throws Exception
    {    
       // modelo.addAttribute("usuarioLogueado", (Postulante)sesion.getAttribute("usuarioLogueado"));        

        List<Postulacion> listaPostulacionesPorCliente = servicioPostulaciones.listaPostulacionesPorPostulante(servicioPostulantes.obtener(usuarioLogueado.getName()));

        if(criterio!=null&&!criterio.isEmpty())
        {
            listaPostulacionesPorCliente = servicioPostulaciones.buscarPorCriterio(criterio);
        }
        else
        {
            listaPostulacionesPorCliente = servicioPostulaciones.listaPostulacionesPorPostulante(servicioPostulantes.obtener(usuarioLogueado.getName()));
        }

        modelo.addAttribute("postulacionesPostulante", listaPostulacionesPorCliente);

        return "postulaciones/lista";        
    }

    

    @GetMapping("/ofertas/{id}/postulantes")
public String verPostulantesDeOferta(@PathVariable Integer id, Model modelo) throws Exception {

    //obtenemos la lista de postulantes desde el servicio
    List<Postulante> postulantes = servicioPostulaciones.obtenerPostulantesPorOferta(id);

    //la agregamos al modelo
    modelo.addAttribute("postulantes", postulantes);

    // Pasamos también el id de la oferta por si lo necesitamos en la vista
    modelo.addAttribute("idOferta", id);

    return "ofertas/postulantes"; // nombre de tu vista
}


    @GetMapping("/listaFiltro")
    public String listaFiltro(String estado,
    Model modelo, 
    Principal usuarioLogueado) throws Exception
    {    
        Postulante postulante = servicioPostulantes.obtener(usuarioLogueado.getName());
        //creo un nuevo List de ofertas y lo inicializo
        List<Oferta> ofertasFiltradas = new ArrayList<>();
        switch (estado) {
            case "postulado":
            //obtengo postulaciones de ese usuario postulante
                List<Postulacion> postulaciones = servicioPostulaciones.listaPostulacionesPorPostulante(postulante);
            //recorro postulaciones y por cada una obtengo las ofertas y las agrego a la lista
                for (Postulacion post : postulaciones) {
                    ofertasFiltradas.add(post.getOferta());
                }
                //agrego a la vista esta lista de ofertas recién creada
                modelo.addAttribute("ofertas", ofertasFiltradas);
                break;
        case "sinPostular":
                List<Oferta> ofertasTodas = servicioOfertas.listaOfertasVigentes();
                List<Postulacion> postulacionesHechas = servicioPostulaciones.listaPostulacionesPorPostulante(postulante);
                List<Oferta> ofertasPostuladas = new ArrayList<>();
                for (Postulacion post : postulacionesHechas) {
                    ofertasPostuladas.add(post.getOferta());
                }
                ofertasFiltradas = ofertasTodas.stream()
                .filter(oferta -> !ofertasPostuladas.contains(oferta)).collect(Collectors.toList());
                modelo.addAttribute("ofertas", ofertasFiltradas);
                break;
            default:
            modelo.addAttribute("ofertas", servicioOfertas.listaOfertasVigentes());
                break;
        }

           return "ofertas/lista";
    }



}
