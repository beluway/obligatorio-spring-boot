package com.bios.edu.uy.obligatorio2025.Controladores;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Servicios.*;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;


@Controller
@RequestMapping("/ofertas")
public class ControladorOfertas {

    @Autowired
    private IServicioClientes servicioClientes;

   
    @Autowired
    private IServicioOfertas servicioOfertas;
 
    @Autowired
    private IServicioPostulaciones servicioPostulaciones;

    @Autowired
    private IServicioAreas servicioAreas;




@GetMapping("/crear")
    public String crearOferta( Principal usuarioLogueado, Model modelo) throws Exception
    {

          modelo.addAttribute("ofertas", new Oferta());
          modelo.addAttribute("areas", servicioAreas.listaAreas());
         modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));

        return "ofertas/crear";
    }



   @PostMapping("/crear") 
    public String procesarCrearOferta (@ModelAttribute @Valid Oferta ofertas, 
    BindingResult resultado,
    Model modelo,
    Principal usuarioLogueado)  throws ExcepcionBiosWork
    {

   if (resultado.hasErrors()) {
       modelo.addAttribute("ofertas", ofertas); //EL OBJETO OFERTA SE GUARDA EN MEMORIA
      modelo.addAttribute("areas", servicioAreas.listaAreas());
        return "ofertas/crear";
    }

        //SE SETEA EL CLIENTE QUE CREA LA OFERTA
        ofertas.setCliente(servicioClientes.obtener(usuarioLogueado.getName()));

        //SE SETEA LA FECHA DE HO
        ofertas.setFechaPublicacion(LocalDate.now());
        ofertas.getArea().setAsignada(true);


       if(!ofertas.getFechaCierre().isBefore(LocalDate.now()))
       {
         try {
            servicioOfertas.agregar(ofertas);
        } catch (ExcepcionBiosWork e) {
            modelo.addAttribute("mensaje", e.getMessage().toString());
            return "ofertas/crear";
        }

        return "redirect:/ofertas/lista";
       }
         else
         {
          modelo.addAttribute("mensaje", "La fecha de cierre no puede ser anterior a la fecha actual");
           modelo.addAttribute("ofertas", ofertas); //SE GUARDA LA OFERTA EN MEMORIA 
            modelo.addAttribute("areas", servicioAreas.listaAreas());
           return "ofertas/crear";
         }

    }

    @GetMapping("/eliminar")

    public String eliminarOferta(Model modelo,@RequestParam Integer codigo, Principal usuarioLogueado) throws Exception{
      
        Oferta oferta = servicioOfertas.obtener(codigo);

        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("oferta", oferta);

        return "ofertas/eliminar";

    }

    @PostMapping("/eliminar")
    public String procesarEliminarOferta(@ModelAttribute @Valid Oferta oferta,  BindingResult resultado, Model modelo)throws Exception
    {
        //PRIMERO SE ELIMINAN LAS POSTULACIONES
       // servicioPostulaciones.eliminarConOferta(oferta);   

        //DESPUES DE ELIMINA LA OFERTA
        servicioOfertas.eliminar(oferta.getId());      

        return "redirect:/ofertas/listaPorCliente";
    }
    
    
    @GetMapping("/modificar")
public String mostrarFormularioModificar(@RequestParam("codigo") Integer codigo,
                                         Model modelo,
                                         Principal usuarioLogueado) throws Exception {
    Oferta oferta = servicioOfertas.obtener(codigo);

    if (oferta == null) {
        return "redirect:/ofertas/lista";
    }

    if (oferta.getArea() == null) {
        oferta.setArea(new Area());
    }

    List<Area> areas = servicioAreas.listaAreas();

    modelo.addAttribute("oferta", oferta);
    modelo.addAttribute("areas", areas);
    modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));

    return "ofertas/modificar";
}



@PostMapping("/modificar")
public String procesarModificarOferta(@ModelAttribute("oferta") @Valid Oferta oferta,
                                      BindingResult resultado,
                                      Model modelo,
                                      RedirectAttributes attributes) throws Exception {

    if (resultado.hasErrors()) {
        modelo.addAttribute("areas", servicioAreas.listaAreas());
        return "ofertas/modificar";
    }

    Oferta ofertaEncontrada = servicioOfertas.obtener(oferta.getId());
    if (ofertaEncontrada == null) {
        attributes.addFlashAttribute("error", "La oferta no existe.");
        return "redirect:/ofertas/lista";
    }

    // Solo modificamos el campo permitido
    ofertaEncontrada.setFechaCierre(oferta.getFechaCierre());

    servicioOfertas.modificar(ofertaEncontrada);

    attributes.addFlashAttribute("exito", "Oferta modificada correctamente.");

    return "redirect:/ofertas/lista";
}

    

    @GetMapping("/ver")    
    public String verOferta(@RequestParam Integer codigo, Model modelo, Principal usuarioLogueado) throws Exception {

       Oferta oferta = servicioOfertas.obtener(codigo);
       modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
       modelo.addAttribute("oferta", oferta);

        // 🔹 Verificar si el usuario ya se postuló
        String usuario = usuarioLogueado.getName();
        Optional<Postulacion> postulacion = servicioPostulaciones.obtener(codigo, usuario);

        boolean yaPostulado = postulacion.isPresent();
        modelo.addAttribute("yaPostulado", yaPostulado);

        modelo.addAttribute("fechaActual", LocalDate.now());

        return "ofertas/ver";
    }   







    @GetMapping("/lista")    
    public String listarOfertas(String criterio, Model modelo, Principal usuarioLogueado) throws Exception {
       //ACA SE SACA LA LISTA DESDE LA BASE DE DATOS

        /* LocalDate fechaActual = LocalDate.now(); */

       

        //ESTO MUESTRA LAS VIGENTES
        List<Oferta> ofertas = servicioOfertas.listaOfertasVigentes();

        if(criterio!=null && !criterio.isEmpty())
        {
            ofertas =servicioOfertas.buscarPorCriterio(criterio);
        }
        else
        {
            ofertas = servicioOfertas.listaOfertasVigentes();
        }


        modelo.addAttribute("ofertas", ofertas);
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));

       
    

     return "ofertas/lista"; //nombre de la vista
 }






    @GetMapping("/listaPorCliente")
    public String listaOfertas(Model modelo, Principal usuarioLogueado) throws Exception {
       
        List<Oferta> OfertasCliente = servicioOfertas.listaOfertasCliente(servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("listaOfertasCliente", OfertasCliente);

        modelo.addAttribute("fechaActual", LocalDate.now());

        return "ofertas/listaPorCliente";
    }   






}

