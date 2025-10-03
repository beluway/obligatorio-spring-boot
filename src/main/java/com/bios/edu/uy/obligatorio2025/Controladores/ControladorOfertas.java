package com.bios.edu.uy.obligatorio2025.Controladores;
import java.util.Date;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model ;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;

import com.bios.edu.uy.obligatorio2025.Servicios.IServicioAreas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;
import com.bios.edu.uy.obligatorio2025.Servicios.ServicioAreas;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/ofertas")
public class ControladorOfertas {

   
       @Autowired
    private IServicioOfertas servicioOfertas;
 
/*     @Autowired
    private IRepositorioAreas repositorioAreas; */

    @Autowired
    private IServicioAreas servicioAreas;



    @GetMapping("/crear")
    public String crearOferta(@ModelAttribute Oferta ofertas, HttpSession sesion, Model modelo)
    {
         modelo.addAttribute("usuarioLogueado", (Cliente)sesion.getAttribute("usuarioLogueado"));   
         modelo.addAttribute("ofertas", new Oferta());       
         modelo.addAttribute("areas", servicioAreas.listaAreas());

        return "ofertas/crear";        
    }



    @PostMapping("/crear") 
    public String procesarCrearOferta (@ModelAttribute @Valid Oferta ofertas, BindingResult resultado, Model modelo,HttpSession sesion)  throws Exception
    {               
        //SE SETEA EL CLIENTE QUE CREA LA OFERTA
        ofertas.setCliente((Cliente)sesion.getAttribute("usuarioLogueado"));
    
        //SE SETEA LA FECHA DE HO
        ofertas.setFechaPublicacion(LocalDate.now());

    /*     Area areaParaOferta = servicioAreas.obtener(modelo.getAttribute("areas"));
 */

        //SE TRAE EL USUARIO LOGUEADO DESDE LA SESION
        sesion.getAttribute("usuarioLogueado");            

        servicioOfertas.agregar(ofertas);

        return "redirect:/ofertas/lista";
    }


    @GetMapping("/eliminar")

    public String eliminarOferta(Model modelo, @RequestParam Integer codigo) throws Exception{
      
        Oferta oferta = servicioOfertas.obtener(codigo);

        modelo.addAttribute("oferta", oferta);

        return "ofertas/eliminar";

    }

    @PostMapping("/eliminar")
    public String procesarEliminarOferta(@ModelAttribute @Valid Oferta ofertas, Model modelo, BindingResult resultado)  {
              
        return "redirect:/ofertas/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String modificarOferta(Model modelo, @RequestParam Integer codigo) throws Exception {
      
        Oferta oferta = servicioOfertas.obtener(codigo);

        if (oferta ==null) {
            return "redirect:/ofertas/lista"; // no existe la vista
        }

        if (oferta.getArea() == null) {
        oferta.setArea(new Area()); // inicializar área para evitar null
        }

        //muestro todas las áreas que hay para elegir
        List<Area> areas = servicioAreas.listaAreas();

        modelo.addAttribute("oferta", oferta);
        modelo.addAttribute("areas", areas);
        
        return "ofertas/modificar";
    }
    

    @PostMapping("/modificar")
    public String procesarModificarOferta(@ModelAttribute @Valid Oferta ofertas, Model modelo, @RequestParam Integer codigo,BindingResult resultado) throws Exception {

        Oferta oferta = servicioOfertas.obtener(codigo);
        modelo.addAttribute("oferta", oferta);
       
        return "redirect:/ofertas/modificar";
    }
    

    @GetMapping("/ver")    
    public String verOferta(@RequestParam Integer codigo, Model modelo) throws Exception {

       Oferta oferta = servicioOfertas.obtener(codigo);
       modelo.addAttribute("oferta", oferta);

        return "ofertas/ver";
    }   


    @GetMapping("/lista")    
    public String listarOfertas(Model modelo, HttpSession sesion) throws Exception {
       //ACA SE SACA LA LISTA DESDE LA BASE DE DATOS
        List<Oferta> ofertas = servicioOfertas.listaOfertasVigentes();

        modelo.addAttribute("ofertas", ofertas);
         modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        return "ofertas/lista"; //nombre de la vista
    } 


    @GetMapping("/listaPorCliente")
    public String listaOfertas(Model modelo, HttpSession sesion, @RequestParam Cliente cliente) {
       
        List<Oferta> OfertasCliente = servicioOfertas.listaOfertasCliente(cliente);


        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        modelo.addAttribute("listaOfertasCliente", OfertasCliente);

        return "ofertas/listaPorCliente";

    }
    

}
