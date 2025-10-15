package com.bios.edu.uy.obligatorio2025.Controladores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulaciones;
import com.bios.edu.uy.obligatorio2025.Servicios.ServicioPostulantes;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/postulantes")
public class ControladorPostulantes {

    @Autowired
    private ServicioPostulantes servicioPostulantes;

     @Autowired
    private IServicioPostulaciones servicioPostulaciones;

    @Autowired
    PasswordEncoder codificador;
       
    @GetMapping("/crear")
    public String postulanteCrear(Model modelo, HttpSession sesion)
    {            
     /*    modelo.addAttribute("postulante", new Postulante());

         Object usuario = sesion.getAttribute("usuarioLogueado");
        if (usuario != null) {
            modelo.addAttribute("usuarioLogueado", usuario);
        } */
        return "postulantes/crear";
        
    }


    
    @PostMapping("/crear")
    public String postulanteCrear (@ModelAttribute @Valid Postulante postulante,  
    BindingResult resultado, 
    Model modelo, 
    RedirectAttributes atributos) throws Exception
    {             
          if(resultado.hasErrors()){
            return "postulantes/crear";
          }


          // SE OBTIENE COMO MULTIPARTE EL .PDF
          MultipartFile pdf = postulante.getPdf();

    // Validación del PDF
    if (pdf == null || pdf.isEmpty()) {
        modelo.addAttribute("errorPdf", "Debe subir un archivo PDF");
        return "postulantes/crear";
    }
        // Validar tipo MIME del archivo
    if (!"application/pdf".equalsIgnoreCase(pdf.getContentType())) {
        modelo.addAttribute("errorPdf", "El archivo debe ser un PDF válido");
        return "postulantes/crear";
    }

        
        if (servicioPostulantes.MayorEdad(postulante.getFechanacimiento())==false) {
        modelo.addAttribute("mensaje", "ATENCIÓN: No es mayor de Edad");
        return "/postulantes/crear";
        }


        //formateo la fecha y la asigno
        LocalDate fechaFormateada =  LocalDate.parse(postulante.getFechanacimiento().toString());

        postulante.setFechanacimiento(fechaFormateada);


        //ACA OBTENGO LA CARPETA DE DESTINO
        
        File  carpetaDestino= new File("C:/ArchivosSubidos");

        //si no existe la carpeta desitno, SE CREA        
        if (!carpetaDestino.exists()) carpetaDestino.mkdirs();
     
         File archivoDestino = new File(carpetaDestino, postulante.getCedula().toString()+".pdf");

         //SE CODIFICA LA CONTRASEÑA
        postulante.setClave(codificador.encode(postulante.getClave()));

        try
        { 
            pdf.transferTo(archivoDestino);

           // postulante.setActivo(true);

            atributos.addFlashAttribute("mensaje","archivo subido !");

            postulante.setCantidadPostulaciones(0);

            postulante.setActivo(true);


            servicioPostulantes.agregar(postulante);  


            atributos.addFlashAttribute("mensaje", "Postulante guardado correctamente.");
            return "redirect:/postulantes/lista";

        }

        catch(Exception ex)
        {
            atributos.addFlashAttribute("mensaje", "no se puede subir el archivo"+ex.getMessage());
        }

        atributos.addFlashAttribute("mensaje","Postulante creado con éxito");
          return "redirect:/postulantes/crear";

    }


    @GetMapping("/eliminar")

    public String postulanteEliminar(Model modelo, HttpSession sesion) 
    {    
        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));
        return "postulantes/eliminar";

    }

    @PostMapping("/eliminar")
    public String postulanteEliminar(@ModelAttribute @Valid Postulante postulante , 
    BindingResult resultado, 
    Model modelo) throws Exception 
    {
         servicioPostulaciones.eliminarConPostulante(postulante);
         
         servicioPostulantes.eliminar(postulante.getUsuario());
        
        return "redirect:/home/index";
    }
    
    
    @GetMapping("/modificar")
    public String postulanteModificar(Model modelo, HttpSession sesion, String usuario) 
    {      
       /*  Postulante postulante = servicioPostulantes.buscar(usuario);
        
        modelo.addAttribute("postulante", postulante);

        modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));     */
        
        return "postulantes/modificar";
    }
    
    @PostMapping("/modificar")
    public String postulanteModificar(@ModelAttribute @Valid Postulante postulante, BindingResult resultado,
    Model modelo, 
    RedirectAttributes atributos) throws Exception
    {             
          if(resultado.hasErrors()){
            return "postulantes/crear";
          }
          
          servicioPostulantes.modificar(postulante);

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
