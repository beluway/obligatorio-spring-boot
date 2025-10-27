package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulaciones;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulantes;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioUsuarios;
//import com.bios.edu.uy.obligatorio2025.Servicios.ServicioClientes;
//import com.bios.edu.uy.obligatorio2025.Servicios.ServicioPostulantes;
//import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/home")
public class ControladorHome {

   

   
   @Autowired
    private IServicioUsuarios servicioUsuarios;

 
    @Autowired
    private IServicioPostulantes servicioPostulantes;
 
     
        @Autowired
    private IServicioPostulaciones servicioPostulaciones;
  
    @GetMapping("/index") 
    public String index() {
         
         return "home/index";   
    }    

    @GetMapping("/ingresar") 
    public String ingresar()
    {        
           
             return "/home/ingresar";           
    }    

    

    @PostMapping("/ingresar")
    public String ingresar(String usuario, Model modelo) throws ExcepcionBiosWork {
     
         //ACA VA EL ACCESO A LA CAPA DE DATOS DE USUARIO Y PREGUNTA SI EXISTE EL USUARIO   

        Usuario usuarioLogin = servicioUsuarios.usuarioParaLogin(usuario);

        
        if(usuarioLogin!=null)
        {
            modelo.addAttribute("usuarioLogin",usuarioLogin.getUsuario());
        
             //SI EXISTE EL USUARIO, SE PASA A LA PÁGINA DE LOGIN
            return "home/login";

        }
        else
        {   //SI NO EXISTE, SE QUEDA EN EL INDEX
         modelo.addAttribute("mensaje","El usuario que indicó no existe, por favor registrarse.");
            return "home/ingresar";
        }      
    }
    



    /// PARA ESTA VISTA HACER MASTERPAGE
    @GetMapping("/main") 
    public String main(Model modelo, Principal usuarioLogueado) throws Exception {
        
            

               Authentication autorizacion = (Authentication)usuarioLogueado;

               String rol = autorizacion.getAuthorities().stream().findFirst().map(Object::toString).orElse(null);

               switch (rol) {
                  case "postulante":
                  
      Postulante postulante = servicioPostulantes.obtener(usuarioLogueado.getName());

         //SE OBTIENEN TODAS LAS POSTULACIONES EL POSTULANTE
    List<Postulacion> listaPostulaciones = servicioPostulaciones.listaPostulacionesPorPostulante(postulante);

    //**** ESTE METODDO LO QUE HACE ES DESCONTAR 1 SEGUN LA CANTIDAD DE POSTULACIONES VENCIDAS.*/
    //DE TODAS LAS POSTULACIONES
    for(Postulacion P : listaPostulaciones)
    {

        if(P.getOferta().getFechaCierre().isBefore(LocalDate.now()))
        {

            if(P.getPostulante().getCantidadPostulaciones()>0)
            {

            int cantidad =     P.getPostulante().getCantidadPostulaciones()-1;
       /*     P.getPostulante().setCantidadPostulaciones(cantidadPostulacionesActulizadasPorOfertasVencidas-1); */
            
           //SE ACTUALIZA LA CANTIDAD DE POSTULACIONES
          servicioPostulantes.actualizarCantidad(usuarioLogueado.getName(), cantidad);  
            }

        }
    } 
                break;
               
                  default:
                     break;
               }


            return "home/main";
    }    


}
