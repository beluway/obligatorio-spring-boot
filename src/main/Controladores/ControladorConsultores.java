import org.springframework.stereotype.Controller;



@Controller
@RequestMapping("/consultores")
public class ControladorConsultores {
 

  @GetMapping("/main")
    public String consultorCrear(@ModelAttribute consultores consultor)
    {
    
        return "consultores/main";
        
    }

     @GetMapping("/crear")
    public String consultorCrear(@ModelAttribute consultores consultor)
    {
    
        return "consultores/crear";
        
    }

    @PostMapping("/crear")
    public String consultorCrear (@ModelAttribute @Validated consultores consultor, Model modelo, BindingResult resultado) 
    {           
        return "consultores/crear";
    }


    @GetMapping("/eliminar")

    public String consultorEliminar(@ModelAttribute consultores consultor) {
      
        return "consultores/eliminar";

    }

    @PostMapping("/eliminar")
    public String consultorEliminar(@ModelAttribute @Validated consultores consultor, Model modelo, BindingResult resultado)  {
              
        return "consultores/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String consultorModificar(@ModelAttribute consultores consultor) {
      
        
        return "consultores/modificar";
    }
    
    @PostMapping("/modificar")
    public String consultorModificar(@ModelAttribute @Validated consultores consultor, Model modelo, BindingResult resultado) {
       
        return "consultores/modificar";
    }
    

    @GetMapping("/ver")    
    public String consultorVer(@ModelAttribute consultores consultor) {
       

        return "consultores/ver";
    }   

}
