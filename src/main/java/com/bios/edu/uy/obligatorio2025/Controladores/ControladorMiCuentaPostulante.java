package com.bios.edu.uy.obligatorio2025.Controladores;

import java.io.File;
import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulantes;
import com.bios.edu.uy.obligatorio2025.utilidades.UtilidadesArchivos;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
@RequestMapping("/micuentaP")
public class ControladorMiCuentaPostulante {
    


  @Autowired
  private IServicioPostulantes servicioPostulantes;

  @Autowired
  PasswordEncoder codificador; 

    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) throws Exception {       

        Postulante postulante= servicioPostulantes.obtener(usuarioLogueado.getName());

        
          File archivoPDF = new File("C:/ArchivosSubidos/" + postulante.getCedula() + ".pdf");
          if (archivoPDF !=null) {
            modelo.addAttribute("cvDisponible", archivoPDF.exists());
          }
          
             File imagen = new File("C:/ArchivosSubidos/" + postulante.getCedula() + ".jpeg");
          if (imagen !=null) {
            modelo.addAttribute("imagenDisponible", imagen.exists());
          }
        

        postulante.setClave("");        

        modelo.addAttribute("postulante", postulante);

        return "/micuentaP/ver";
    }
    
    

    @PostMapping("/ver")
    public String modificarPostulante(
            @ModelAttribute @Valid Postulante postulante,
            BindingResult resultado,
            Model modelo,
            RedirectAttributes atributos) throws Exception {

            if (resultado.hasErrors()) {
                modelo.addAttribute("postulante", postulante);
                return "micuentaP/ver"; // queda en la misma página si hay errores
            } 


  MultipartFile pdf = postulante.getPdf();

                
       
            if(!postulante.getPdf().isEmpty()){

              File pdfPostulante = new File("C:/ArchivosSubidos/"+postulante.getCedula()+".pdf");

              if(pdfPostulante.exists())
              {
                  pdfPostulante.delete();
              }

          // SE OBTIENE COMO MULTIPARTE EL .PDF
         /*           MultipartFile pdf = postulante.getPdf();
        UtilidadesArchivos.guardarPdf(pdf.getBytes(),"C:/ArchivosSubidos",postulante.getCedula().toString(), 
                "pdf"); */
               
    File  carpetaDestino= new File("C:/ArchivosSubidos");
    File archivoDestino = new File(carpetaDestino, postulante.getCedula().toString()+".pdf");
    pdf.transferTo(archivoDestino);

   }

    MultipartFile imagen = postulante.getImagen();

    if (!imagen.isEmpty()) {
      //primero borro la que ya había
      File imagenPostulante = new File("C:/ArchivosSubidos/"+postulante.getCedula()+".jpeg");
        if(imagenPostulante.exists())
        {
           imagenPostulante.delete();
        }
        //ahora sí guardo la nueva
      UtilidadesArchivos.guardarImagen(imagen.getBytes(), "C:/ArchivosSubidos", postulante.getCedula().toString(), "jpeg");
       
    }

        servicioPostulantes.modificar(postulante);

        // Recargar los datos actualizados
        modelo.addAttribute("postulante", postulante);
        atributos.addFlashAttribute("mensaje", "Datos modificados correctamente");

        return "redirect:/micuentaP/ver"; 

     }
        

      @PostMapping("/eliminar")
      public String eliminar (Model modelo, RedirectAttributes attributes,  
       @RequestParam("codigoPostulante")String codigoPostulante, 
       Principal usuarioLogueado) throws Exception 
      {         
       
         Postulante encontrado = servicioPostulantes.obtener(codigoPostulante);
            /* Optional<Postulacion> postulacionExistente = servicioPostulaciones.findByOfertaAndPostulante(postulacion.getOferta(),postulacion.getPostulante());   */
            
            if (encontrado==null) {

                throw new ExcepcionNoExiste("El postulante no se encontró");              
            } 

            try
            {
           
              servicioPostulantes.eliminar(encontrado.getUsuario());        

              attributes.addFlashAttribute("mensaje","Postulación eliminada con éxito.");
              return "redirect:/home/index";

            } 
            catch (Exception ex) {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "micuentaP/ver";
            }                
      }


}