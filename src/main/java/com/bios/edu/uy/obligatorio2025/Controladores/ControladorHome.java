package com.bios.edu.uy.obligatorio2025.Controladores;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioUsuarios;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/home")
public class ControladorHome {
   

HttpSession sessionUsuario;

   
   @Autowired
    private IServicioUsuarios servicioUsuarios;
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("servicioUsuarioSession")

    @GetMapping("/index") 
    public String index() {
         
       
        return "home/index";       
    }    

    @GetMapping("/ingresar") 
    public String ingresar(Principal usuarioLogueado)
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
    


    /* @GetMapping("/login")
    public String login(HttpSession session) {
      
       /*  //EL USUARIO YA ESTA LOGUEADO (EXISTE EN LA SESION) ??
        if(session.getAttribute("usuarioLogueado")==null)    
        {   
            //NO, REDIRIGE AL INDEX
            return "redirect:/home/index";
        }
        */
        //SE QUEDA EN EL LOGIN (PARA INGRESAR LA CONTRASEÑA)
      /*    return "home/login"; 
    }     
 */

  /*   @PostMapping("/login")
    public String login(@ModelAttribute Usuario usuario, Model modelo, BindingResult resultado, HttpSession sesion) {
       
        // SE BUSCA SI EXISTE EN LA CAPA USUARIO CON LOS DATOS PASADOS

        //SI EXISTE EL USUARIO, SE PREGUNTA DE QUE TIPO ES. 
            //DEPENDIENDO EL TIPO SE REDIRIGE A LA PÁGINA QUE LE TOQUE

        try
        {
            Usuario existente = servicioUsuarios.usuarioParaLogin(usuario.getUsuario());
           


            if(existente!=null && existente.isActivo())
            {
                if(passwordEncoder.matches(usuario.getClave(), existente.getClave()))
                {
 //*********************  USUARIO EN SESION (SIN PREGUNTAR EL TIPO)  *************************
              sesion.setAttribute("usuarioLogueado", existente);   


                   return "/home/main"; 
                }

                else
                {
                modelo.addAttribute("mensaje", "Contraseña incorrecta");
                }         
             
      
            }

            else {
            modelo.addAttribute("mensaje", "Usuario no existe o está inactivo");
         }
         return "/home/login";
        }

        catch (Exception ex)
        {
           modelo.addAttribute("mensaje","Hubo un error "+ ex.getMessage());
           return "clientes/crear";
        }
        
    } */




    /// PARA ESTA VISTA HACER MASTERPAGE
    @GetMapping("/main") 
    public String main(Model modelo, HttpSession sesion) {
        
             if (sesion.getAttribute("usuarioLogueado") instanceof Consultor) {
                modelo.addAttribute("usuarioLogueado", (Consultor)sesion.getAttribute("usuarioLogueado"));
             }

           if (sesion.getAttribute("usuarioLogueado") instanceof Cliente) {
                modelo.addAttribute("usuarioLogueado", (Cliente)sesion.getAttribute("usuarioLogueado"));
             }

             if (sesion.getAttribute("usuarioLogueado") instanceof Postulante) {
                modelo.addAttribute("usuarioLogueado", (Postulante)sesion.getAttribute("usuarioLogueado"));
             }

            /*modelo.addAttribute("Cliente", sesion.getAttribute("usuarioLogueado") instanceof Cliente);

            modelo.addAttribute("Postulante", sesion.getAttribute("usuarioLogueado") instanceof Postulante);

            modelo.addAttribute("Consultor", sesion.getAttribute("usuarioLogueado") instanceof Consultor); */

            return "home/main";
    }
    
    @GetMapping("/deslogueo")
    public String deslogueo(Model modelo,HttpSession session) {
             
               //SE ELIMINA LA SESIÓN DE USUARIO
        session.removeAttribute("usuarioLogueado");

          //SE BORRAN TODOS LOS DATOS DE SESION
        session.invalidate();

        return "home/index";

    }
    

}
