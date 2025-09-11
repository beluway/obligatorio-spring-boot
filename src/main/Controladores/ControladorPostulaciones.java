import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.*;


@Controller
@RequestMapping("/postulaciones")
public class ControladorPostulaciones {
   
    @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute postulaciones postulacion)
    {    
        return "postulaciones/crear";        
    }


    @PostMapping("/crear")
    public String clienteCrear (@ModelAttribute @Valid postulaciones postulacion, Model modelo, BindingResult resultado) 
    {           
        return "postulaciones/crear";
    }


     @GetMapping("/eliminar")
    public String clienteCrear(@ModelAttribute postulaciones postulacion)
    {    
        return "postulaciones/eliminar";        
    }


    @PostMapping("/eliminar")
    public String clienteCrear (@ModelAttribute @Valid postulaciones postulacion, Model modelo, BindingResult resultado) 
    {           
        return "postulaciones/eliminar";
    }


     @GetMapping("/ver")
    public String clienteCrear(@ModelAttribute postulaciones postulacion)
    {    
        return "postulaciones/ver";        
    }


}
