package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioUsuarios;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/home")
public class ControladorHome {
   

HttpSession sessionUsuario;

   
       @Autowired
    private IServicioUsuarios servicioUsuarios;
    
    @Qualifier("servicioUsuarioSession")

    @GetMapping("/index") 
    public String index() {
      
    
      /*   Cliente usu=null;  // = aca va el metodo del servicio para la existencia usuarios
        //BUSCAR USUARIO ................controladorClientes

        if(sessionUsuario!=null)
        {       
            return "redirect:/home/login";
        } */
       
        return "home/index";       
    }    

    @PostMapping("/index")
    public String index(String usu, Model modelo) {
       
         //ACA VA EL ACCESO A LA CAPA DE DATOS DE USUARIO Y PREGUNTA SI EXISTE EL USUARIO   

        Usuario usuarioLogin = servicioUsuarios.usuarioParaLogin(usu);

        if(usuarioLogin!=null)
        {
            modelo.addAttribute("usuarioLogin",usu);
        
             //SI EXISTE EL USUARIO, SE PASA A LA PÁGINA DE LOGIN
            return "redirect:/home/login";

        }
        else
        {   //SI NO EXISTE, SE QUEDA EN EL INDEX
            return "home/index";
        }
      
    }
    
    @GetMapping("/login")
    public String login(HttpSession session) {
      
        //EL USUARIO YA ESTA LOGUEADO (EXISTE EN LA SESION) ??
        if(session.getAttribute("usuarioLogueado")==null)    
        {   
            //NO, REDIRIGE AL INDEX
            return "redirect:/home/index";
        }

        //SE QUEDA EN EL LOGIN (PARA INGRESAR LA CONTRASEÑA)
         return "home/login";
    }    


    @PostMapping("/login")
    public String login(@ModelAttribute @Valid Usuario usuario, Model modelo, BindingResult resultado, HttpSession sesion) {
       
        // SE BUSCA SI EXISTE EN LA CAPA USUARIO CON LOS DATOS PASADOS

        //SI EXISTE EL USUARIO, SE PREGUNTA DE QUE TIPO ES. 
            //DEPENDIENDO EL TIPO SE REDIRIGE A LA PÁGINA QUE LE TOQUE

            if(usuario!=null)
            {

                if( usuario instanceof Consultor)    
                {
                    return "consultores/main";
                }
                else if(usuario instanceof Cliente)
                {
                    return "clientes/main";
                }
            
                else if(usuario instanceof Postulante)
                {
                    return "postulantes/main";
                }

            //SI SE ENCONTRÓ EL USUARIO COMPLETO (USU + PASS)
            sessionUsuario.setAttribute("usuarioLogueado", usuario);     
      
            }
      
        return "redirect:/postulantes/main";
    }

}
