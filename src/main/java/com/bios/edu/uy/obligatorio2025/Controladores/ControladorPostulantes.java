package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Servicios.ServicioPostulantes;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/postulantes")
public class ControladorPostulantes {

    @Autowired
    private ServicioPostulantes servicioPostulantes;

       
    @GetMapping("/crear")
    public String postulanteCrear(Model modelo, HttpSession sesion)
    { 
        if(((Postulante)sesion.getAttribute("usuarioLogueado")).getCantidadPostulaciones()>=3)
        {
            

            return "redirect/home/main";
        }

        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "postulantes/crear";
        
    }

    @PostMapping("/crear")
    public String postulanteCrear (@ModelAttribute @Valid Postulante postulante,  
    BindingResult resultado, 
    Model modelo, 
     RedirectAttributes atributos) throws Exception
    {             
          if(resultado.hasErrors()){
            atributos.addFlashAttribute("mensaje", "Errores en el formulario");
            return "redirect:/postulantes/crear";
          }


          // SE OBTIENE COMO MULTIPARTE EL .PDF
          MultipartFile pdf = postulante.getPdf();

        if (pdf == null || pdf.isEmpty()) {
        atributos.addFlashAttribute("mensaje", "ATENCIÓN: No se subió ningún archivo");
        return "redirect:/postulantes/crear";
        }

        //formateo la fecha y la asigno
        LocalDate fechaFormateada =  LocalDate.parse(postulante.getFechanacimiento().toString());

        postulante.setFechanacimiento(fechaFormateada);


        //ACA OBTENGO LA CARPETA DE DESTINO
        
        File  carpetaDestino= new File("C:/ArchivosSubidos");

        //si no existe la carpeta desitno, SE CREA        
        if (!carpetaDestino.exists()) carpetaDestino.mkdirs();

     
         File archivoDestino = new File(carpetaDestino, postulante.getCedula().toString()+".pdf");

        try
        { 
            pdf.transferTo(archivoDestino);

            atributos.addFlashAttribute("mensaje","archivo subido !");

            servicioPostulantes.agregar(postulante);          

        }

        catch(Exception ex)
        {
            atributos.addFlashAttribute("mensaje", "no se puede subir el archivo"+ex.getMessage());
        }

          return "redirect:/postulantes/crear";

    }


    @GetMapping("/eliminar")

    public String postulanteEliminar(Model modelo, HttpSession sesion) 
    {
    
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "postulantes/eliminar";

    }

    @PostMapping("/eliminar")
    public String postulanteEliminar(@ModelAttribute @Valid Postulante postulante, Model modelo, BindingResult resultado)  {
              
        modelo.addAttribute("postulante", postulante);
        return "redirect:/postulantes/eliminar";
    }
    
    
    @GetMapping("/modificar")
    public String postulanteModificar(Model modelo, HttpSession sesion, String usuario) 
    {      
        Postulante postulante = servicioPostulantes.buscar(usuario);
        
        modelo.addAttribute("postulante", postulante);

        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));    
        
        return "postulantes/modificar";
    }
    
    @PostMapping("/modificar")
    public String postulanteModificar(@ModelAttribute @Valid Postulante postulante, Model modelo, BindingResult resultado) {
       
        return "redirect:/postulantes/modificar";
    }
    

    @GetMapping("/ver")    
    public String postulanteVer(Model modelo, HttpSession sesion) 
    {
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));

        return "postulantes/ver";
    }   


    
    @PostMapping("/ver")    
    public String postulanteVer(@ModelAttribute Postulante postulantes, BindingResult resultado,  @RequestParam  String accion) {
       
        if("btn_modificar".equals(accion))
        {
            //LLAMA A PERSISTENCIA MODIFICAR

            return "redirect:/postulantes/modificar";
        }
        
        else if("btn_eliminar".equals(accion))
        {
                //ACA APARECE UN POP UP QUE PREGUNTA SI SE ELIMINA O NO

            //LLAMA A PERSISTENCIA ELIMINAR
        }

        return "redirect:postulantes/ver";
    }   

    
    @GetMapping("/lista")
    public String lista(Model modelo, HttpSession sesion) throws Exception {
       
        List<Postulante> listaPostulantes = servicioPostulantes.lista();
        
        //SE SACA LA LISTA DE POSTULANTES DE LA BD 
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        modelo.addAttribute("listaPostulantes", listaPostulantes.toArray());


        return "postulantes/lista";

    }


}
