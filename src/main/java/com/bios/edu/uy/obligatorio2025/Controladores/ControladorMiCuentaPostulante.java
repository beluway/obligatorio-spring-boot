package com.bios.edu.uy.obligatorio2025.Controladores;

import java.io.File;
import java.io.IOException;
import java.security.Principal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
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

////???????
         Integer cantidadPostulacionesActulizadasPorOfertasVencidas = 3 - postulante.getCantidadPostulaciones();  
         
         modelo.addAttribute("mensajeCantidad", "cabeza, tenes "+cantidadPostulacionesActulizadasPorOfertasVencidas.toString()+" postulaciones permitidas");

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

            if (resultado.hasErrors()) 
            {
                    return "micuentaP/ver"; // queda en la misma página si hay errores
            } 

            if (!servicioPostulantes.MayorEdad(postulante.getFechanacimiento())) {
        //Devolver el objeto del formulario, no el original
        modelo.addAttribute("postulante", postulante); 
        modelo.addAttribute("mensaje2", "Debe ser mayor de edad.");
        return "micuentaP/ver";
    }


     // 2️⃣ Obtener el MultipartFile para el PDF
MultipartFile pdf = postulante.getPdf();


// 3️⃣ Manejo del archivo PDF: Solo proceder si se seleccionó un nuevo PDF
if(!pdf.isEmpty()){
    
    // Ruta completa al archivo antiguo (basada en la cédula)
    File pdfPostulante = new File("C:/ArchivosSubidos/"+postulante.getCedula()+".pdf");

    // Borrar el archivo anterior si existe (ya que se subirá uno nuevo)
    if(pdfPostulante.exists())
    {
        pdfPostulante.delete();
    }
    
    // Crear la carpeta y el destino para el nuevo archivo
    File carpetaDestino= new File("C:/ArchivosSubidos");
    // Asegurarse de que el directorio existe
    if (!carpetaDestino.exists()) {
        carpetaDestino.mkdirs();
    }
    
    File archivoDestino = new File(carpetaDestino, postulante.getCedula().toString()+".pdf");
    
    // Guardar el nuevo PDF
    try {
      pdf.transferTo(archivoDestino);
    } catch (IllegalStateException e) {
      modelo.addAttribute("mensaje", "Hubo un error al guardar el PDF: "+e.getMessage());
      modelo.addAttribute("postulante", postulante); // Asumiendo que has corregido esto
      return "micuentaP/ver";
    } catch (IOException e) {
      modelo.addAttribute("mensaje", "Hubo un error de I/O al guardar el PDF: "+e.getMessage());
      modelo.addAttribute("postulante", postulante); // Asumiendo que has corregido esto
      return "micuentaP/ver";
    }
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