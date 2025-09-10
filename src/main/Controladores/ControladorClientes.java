import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bios.edu.uy.obligatorio2025.*;


@Controller
@RequestMapping("/clientes")
public class ControladorClientes {
        

    @GetMapping("/main")
    public String clienteCrear(@ModelAttribute clientes cliente)
    {
    
        return "clientes/main";        
    }


    @GetMapping("/crear")
    public String clienteCrear(@ModelAttribute clientes cliente)
    {
    
        return "clientes/crear";
        
    }

    @PostMapping("/crear")
    public String clienteCrear (@ModelAttribute @Validated clientes cliente, Model modelo, BindingResult resultado) 
    {           
        return "clientes/crear";
    }


    @GetMapping("/eliminar")

    public String clienteEliminar(@ModelAttribute clientes cliente) {
      
        return "clientes/eliminar";

    }

    @PostMapping("/eliminar")
    public String clienteEliminar(@ModelAttribute @Validated clientes cliente, Model modelo, BindingResult resultado)  {
              
        return "clientes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String clienteModificar(@ModelAttribute clientes cliente) {
      
        
        return "clientes/modificar";
    }
    
    @PostMapping("/modificar")
    public String clientesModificar(@ModelAttribute @Validated clientes cliente, Model modelo, BindingResult resultado) {
       
        return "clientes/modificar";
    }
    

    @GetMapping("/ver")    
    public String clientesVer(@ModelAttribute clientes cliente) {
       

        return "clientes/ver";
    }   

   

}
