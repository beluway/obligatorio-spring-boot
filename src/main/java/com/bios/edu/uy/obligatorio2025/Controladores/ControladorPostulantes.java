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

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioPostulaciones;
import com.bios.edu.uy.obligatorio2025.Servicios.ServicioPostulantes;

import org.springframework.ui.Model;


import jakarta.validation.Valid;



import java.io.File;

import java.security.Principal;
import java.time.LocalDate;

import java.util.List;

@Controller
@RequestMapping("/postulantes")
public class ControladorPostulantes {

    @Autowired
    private ServicioPostulantes servicioPostulantes;

     @Autowired
    private IServicioPostulaciones servicioPostulaciones;

    @Autowired
    private IServicioOfertas servicioOfertas;

       
   @GetMapping("/crear")
public String postulanteCrear(Model modelo) {
    modelo.addAttribute("postulante", new Postulante());
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

        try
        { 
            pdf.transferTo(archivoDestino);

           // postulante.setActivo(true);

            atributos.addFlashAttribute("mensaje","archivo subido !");

            postulante.setCantidadPostulaciones(0);
         
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

    public String postulanteEliminar(Model modelo, Principal usuarioLogueado) throws Exception
    {    
        modelo.addAttribute("usuarioLogueado", servicioPostulantes.obtener(usuarioLogueado.getName()));
        return "postulantes/eliminar";

    }




    @PostMapping("/eliminar")
    public String postulanteEliminar(@ModelAttribute @Valid Postulante postulante , 
    BindingResult resultado, 
    Model modelo) throws Exception 
    {

        File pdfPostulante = new File("C:/ArchivosSubidos/"+postulante.getCedula()+".pdf");

        if(pdfPostulante.exists())
        {
            pdfPostulante.delete();
        }

         servicioPostulaciones.eliminarConPostulante(postulante);
         
         servicioPostulantes.eliminar(postulante.getUsuario());
        
        return "redirect:/home/index";
    }
    
    
    @GetMapping("/modificar")
    public String postulanteModificar(Model modelo, Principal principal)   throws Exception
    {      
        Postulante postulante = servicioPostulantes.buscar(principal.getName());
        
        modelo.addAttribute("postulante", postulante);

       // modelo.addAttribute("usuarioLogueado", sesion.getAttribute("usuarioLogueado"));    
        
        return "postulantes/modificar";
    }



    
    @PostMapping("/modificar")
   public String modificarPostulante(
            @ModelAttribute @Valid Postulante postulante,
            BindingResult resultado,
            Model modelo,
            RedirectAttributes atributos) throws Exception {

        if (resultado.hasErrors()) {
            modelo.addAttribute("usuarioLogueado", postulante);
            return "postulantes/ver"; // queda en la misma página si hay errores
        }

       
      
        // Recargar los datos actualizados
        modelo.addAttribute("usuarioLogueado", postulante);
        atributos.addFlashAttribute("mensaje", "Datos modificados correctamente");

        return "postulantes/ver"; // sigue en ver.html

     }




    @GetMapping("/ver")    
    public String postulanteVer(@RequestParam String usuario,@RequestParam(required = false) Integer idOferta,Model modelo, Principal usuarioLogueado, RedirectAttributes attributes) throws Exception
    {
        modelo.addAttribute("usuarioLogueado", servicioPostulantes.obtener(usuarioLogueado.getName()));

        Postulante postulanteEncontrado= servicioPostulantes.buscar(usuario);

        if (postulanteEncontrado ==null) {
            attributes.addAttribute("error","El postulante con usuario "+usuario+" no existe.");
            return "redirect:/postulantes/lista"; // si no existe
        }

        //si existe el postulante lo agrego al modelo
            modelo.addAttribute("postulante", postulanteEncontrado);

            // Verificar si el archivo PDF existe
            File archivoPDF = new File("C:/ArchivosSubidos/" + postulanteEncontrado.getCedula() + ".pdf");
            if (archivoPDF.exists()) {
                modelo.addAttribute("cvDisponible", true);
            } else {
                modelo.addAttribute("cvDisponible", false);
            }

                // ✅ Si vino desde una oferta, lo pasamos a la vista
            if (idOferta != null) {
                modelo.addAttribute("idOferta", idOferta);
            }

        return "postulantes/ver";
    }   


    
    @PostMapping("/ver")    
    public String postulanteVer(@ModelAttribute Postulante postulantes, BindingResult resultado,  @RequestParam  String accion) throws Exception {
       
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
    public String lista(String criterio,Model modelo, Principal usuarioLogueado) throws Exception {
       
        List<Postulante> listaPostulantes = servicioPostulantes.lista();



            if (criterio != null && !criterio.isEmpty()) {
                listaPostulantes = servicioPostulantes.buscarPorCriterio(criterio);
            }
            else {
                listaPostulantes = servicioPostulantes.lista();
            }


        
        //SE SACA LA LISTA DE POSTULANTES DE LA BD 
        modelo.addAttribute("usuarioLogueado", servicioPostulantes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("listaPostulantes", listaPostulantes.toArray());


        return "postulantes/lista";

    }

}
