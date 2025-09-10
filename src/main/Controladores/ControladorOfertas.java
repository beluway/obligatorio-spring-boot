import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.*;


@Controller
@RequestMapping("/ofertas")
public class ControladorOfertas {
    
    @GetMapping("/main")
    public String clienteCrear(@ModelAttribute ofertas oferta)
    {
    
        return "ofertas/main";
        
    }

     @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute ofertas oferta)
    {
    
        return "ofertas/crear";
        
    }

    @PostMapping("/crear")
    public String clienteCrear (@ModelAttribute @Validated ofertas oferta, Model modelo, BindingResult resultado) 
    {           
        return "ofertas/crear";
    }


    @GetMapping("/eliminar")

    public String clienteEliminar(@ModelAttribute ofertas oferta) {
      
        return "ofertas/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Validated ofertas oferta, Model modelo, BindingResult resultado)  {
              
        return "ofertas/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(@ModelAttribute ofertas oferta) {
      
        
        return "ofertas/modificar";
    }
    
    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Validated ofertas oferta, Model modelo, BindingResult resultado) {
       
        return "ofertas/modificar";
    }
    

    @GetMapping("/ver")    
    public String clientesVer(@ModelAttribute ofertas oferta) {
       

        return "ofertas/ver";
    }   



}
