import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/postulaciones")
public class ControladorPostulaciones {
   
    @GetMapping("/crear")
    public String crear(@ModelAttribute Postulacion postulacion)
    {    
        return "postulaciones/crear";        
    }


    @PostMapping("/crear")
    public String crear (@ModelAttribute @Valid Postulacion postulacion, Model modelo, BindingResult resultado) 
    {           
        return "postulaciones/crear";
    }


     @GetMapping("/eliminar")
    public String eliminar(@ModelAttribute Postulacion postulacion)
    {    
        return "postulaciones/eliminar";        
    }


    @PostMapping("/eliminar")
    public String eliminar (@ModelAttribute @Valid Postulacion postulacion, Model modelo, BindingResult resultado) 
    {           
        return "postulaciones/eliminar";
    }


     @GetMapping("/ver")
    public String ver(@ModelAttribute Postulacion postulacion)
    {    
        return "postulaciones/ver";        
    }


}
