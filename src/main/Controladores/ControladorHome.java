import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.*;


@Controller
@RequestMapping("/home")
public class ControladorHome {
    
    @GetMapping("/index")
    public String index(@ModelAttribute usuarios usuario) {
      
    
        return "home/index";
    }    

    @PostMapping("/index")
    public String index(@ModelAttribute @Validated usuarios usuario, Model modelo, BindingResult resultado) {
       
        modelo.getAttribute("usu",usuario.getUsuario);
        

        //ACA VA EL ACCESO A LA CAPA DE DATOS DE USUARIO
        
        return "home/login";
    }
    

    @GetMapping("/login")
    public String index(@ModelAttribute usuarios usuario) {
      
        return "home/login";
    }    

    @PostMapping("/login")
    public String index(@ModelAttribute @Validated usuarios usuario, Model modelo, BindingResult resultado) {
       
      
        // SE BUSCA SI EXISTE EN LA CAPA USUARIO CON LOS DATOS PASADOS
        if(modelo.getAttribute("usu",usuario.getUsuario))
        {
            //SI EXISTE EL USUARIO, SE PREGUNTA DE QUE TIPO ES. 
            //DEPENDIENDO EL TIPO SE REDIRIGE A LA PÁGINA QUE LE TOQUE

            if(usuario instanceof consultores)    
            {
                return "consultores/main";
            }
            else if(usuario instanceof clientes)
            {
                return "ofertas/main";
            }
           
            else if(usuario instanceof postulantes)
            {
                return "ofertas/main";
            }
        }
      
      
        return "postulantes/main";
    }

}
