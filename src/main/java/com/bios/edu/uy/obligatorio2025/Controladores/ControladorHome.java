package com.bios.edu.uy.obligatorio2025.Controladores;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioConsultores;
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
    private IServicioClientes servicioClientes;

    @Autowired
    private IServicioPostulantes servicioPostulantes;

    @Autowired
    private IServicioConsultores servicioConsultores;
     


    @GetMapping("/index") 
    public String index() throws Exception{
         
       try{
                   
       }
       catch(Exception ex)
       {
        
       }
        
         return "home/index";   
    }    

    @GetMapping("/ingresar") 
    public String ingresar()
    {        
           
             return "/home/ingresar";           
    }    


    @PostMapping("/ingresar")
    public String ingresar(String usuario, Model modelo) {
     
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
            return "home/ingresar";
        }      
    }
    




    /// PARA ESTA VISTA HACER MASTERPAGE
    @GetMapping("/main") 
    public String main(Model modelo, Principal usuarioLogueado) throws Exception {
        
             if (usuarioLogueado instanceof Consultor) {
                modelo.addAttribute("usuarioLogueado", servicioConsultores.obtener(usuarioLogueado.getName()));
             }

           if (usuarioLogueado instanceof Cliente) {
                modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
             }

             if (usuarioLogueado instanceof Postulante) {
                modelo.addAttribute("usuarioLogueado", servicioPostulantes.obtener(usuarioLogueado.getName()));
             }

            /*modelo.addAttribute("Cliente", sesion.getAttribute("usuarioLogueado") instanceof Cliente);

            modelo.addAttribute("Postulante", sesion.getAttribute("usuarioLogueado") instanceof Postulante);

            modelo.addAttribute("Consultor", sesion.getAttribute("usuarioLogueado") instanceof Consultor); */

            return "home/main";
    }
    

    
/*     @GetMapping("/deslogueo")
    public String deslogueo(Model modelo,Principal usuarioLogueado) throws Exception{
             
        //SE ELIMINA LA SESIÓN DE USUARIO
        usuarioLogueado=null;
        modelo.addAttribute("usuarioLogueado", usuarioLogueado);

          //SE BORRAN TODOS LOS DATOS DE SESION
        //Session.invalidate();
        SecurityContextHolder.clearContext();
        return "home/index";

    } */
    

}
