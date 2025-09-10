import org.springframework.stereotype.Controller;
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
       
    
        return "home/index";
    }
    

     @GetMapping("/login")
    public String index(@ModelAttribute usuarios usuario) {
      
        return "login/index";
    }    

    @PostMapping("/index")
    public String index(@ModelAttribute @Validated usuarios usuario, Model modelo, BindingResult resultado) {
       
        return "login/index";
    }

}
