package com.bios.edu.uy.obligatorio2025.Controladores;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model ;

import com.bios.edu.uy.obligatorio2025.Dominio.areas;
import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioAreas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/ofertas")
public class ControladorOfertas {

    @Autowired
    private IServicioOfertas servicioOfertas;

    @Autowired
    private IServicioAreas servicioAreas;

    @GetMapping("/crear")
    public String crearOferta(@ModelAttribute ofertas ofertas, HttpSession sesion, Model modelo)
    {
         modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));   

        return "ofertas/crear";
        
    }

    @PostMapping("/crear") 
    public String procesarCrearOferta (@ModelAttribute @Valid ofertas ofertas, Model modelo, BindingResult resultado, HttpSession sesion) 
    {               
        //SE TRAE EL USUARIO LOGUEADO DESDE LA SESION
        sesion.getAttribute("usuarioLogueado");
        
        return "redirect:/ofertas/crear";
    }


    @GetMapping("/eliminar")

    public String eliminarOferta(Model modelo, @RequestParam Integer codigo) throws Exception{
      
        ofertas oferta = servicioOfertas.obtener(codigo);

        modelo.addAttribute("oferta", oferta);

        return "ofertas/eliminar";

    }

    @PostMapping("/eliminar")
    public String procesarEliminarOferta(@ModelAttribute @Valid ofertas ofertas, Model modelo, BindingResult resultado)  {
              
        return "redirect:/ofertas/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String modificarOferta(Model modelo, @RequestParam Integer codigo) throws Exception {
      
        ofertas oferta = servicioOfertas.obtener(codigo);

        if (oferta ==null) {
            return "redirect:/ofertas/lista"; // no existe la vista
        }

        if (oferta.getArea() == null) {
        oferta.setArea(new areas()); // inicializar área para evitar null
        }

        //muestro todas las áreas que hay para elegir
        List<areas> areas = servicioAreas.listaAreas();

        modelo.addAttribute("oferta", oferta);
        modelo.addAttribute("areas", areas);
        
        return "ofertas/modificar";
    }
    

    @PostMapping("/modificar")
    public String procesarModificarOferta(@ModelAttribute @Valid ofertas ofertas, Model modelo, @RequestParam Integer codigo,BindingResult resultado) throws Exception {

        ofertas oferta = servicioOfertas.obtener(codigo);
        modelo.addAttribute("oferta", oferta);
       
        return "redirect:/ofertas/modificar";
    }
    

    @GetMapping("/ver")    
    public String verOferta(@RequestParam Integer codigo, Model modelo) throws Exception {

       ofertas oferta = servicioOfertas.obtener(codigo);
       modelo.addAttribute("oferta", oferta);

        return "ofertas/ver";
    }   


    @GetMapping("/lista")    
    public String listarOfertas(Model modelo) throws Exception {
       //ACA SE SACA LA LISTA DESDE LA BASE DE DATOS
        List<ofertas> ofertas = servicioOfertas.listaOfertas();

        modelo.addAttribute("ofertas", ofertas);

        return "ofertas/lista"; //nombre de la vista
    } 

}
