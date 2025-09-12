package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.JmsProperties.Listener.Session;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import com.bios.edu.uy.obligatorio2025.Dominio.usuarios;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioUsuarioSession;
import com.bios.edu.uy.obligatorio2025.Dominio.consultores;
import com.bios.edu.uy.obligatorio2025.Dominio.clientes;
import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;



@Controller
@RequestMapping("/home")
public class ControladorHome {



    @Autowired
    @Qualifier("servicioUsuarioSession")

    private IServicioUsuarioSession usuarioSession;

    @GetMapping("/index") 
    public String index(Model modelo, HttpSession session) {
      
    
        usuarios usu=null;  // = aca va el metodo del servicio para validar usuarios
    
        if(usu!=null)
        {   
            session.setAttribute("usuarioLogueado", usu);           

            return  "home/login";
        }
        else{
             return "home/index";
        }
       
    }    

 

    @PostMapping("/index")
    public String index(@ModelAttribute @Valid usuarios usuario, Model modelo, BindingResult resultado) {
       
        modelo.getAttribute(usuario.getUsuario());
        

        //ACA VA EL ACCESO A LA CAPA DE DATOS DE USUARIO
        
        return "home/login";
    }
    

    @GetMapping("/login")
    public String index(@ModelAttribute usuarios usuario, HttpSession session) {
      
        //EL USUARIO YA ESTA LOGUEADO (EXISTE EN LA SESION) ??
        if(session.getAttribute("usuarioLogueado")==null)    
        {   
            //NO, REDIRIGE AL INDEX
            return "redirect:/home/index";
        }

        //SE QUEDA EN EL LOGIN (PARA INGRESAR LA CONTRASEÑA)
         return "redirec:/home/login";
    }    

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid usuarios usuario, Model modelo, BindingResult resultado) {
       
      
        // SE BUSCA SI EXISTE EN LA CAPA USUARIO CON LOS DATOS PASADOS

        //SI EXISTE EL USUARIO, SE PREGUNTA DE QUE TIPO ES. 
            //DEPENDIENDO EL TIPO SE REDIRIGE A LA PÁGINA QUE LE TOQUE

            if( usuario instanceof consultores)    
            {
                return "consultores/main";
            }
            else if(usuario instanceof clientes)
            {
                return "clientes/main";
            }
           
            else if(usuario instanceof postulantes)
            {
                return "postulantes/main";
            }

   
      
      
        return "postulantes/main";
    }

}
