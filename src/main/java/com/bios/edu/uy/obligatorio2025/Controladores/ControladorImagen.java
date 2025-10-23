package com.bios.edu.uy.obligatorio2025.Controladores;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bios.edu.uy.obligatorio2025.Servicios.ServicioPostulantes;

@Controller
public class ControladorImagen {
    
   @Autowired
    private ServicioPostulantes servicioPostulante;

    @GetMapping("/foto/{nombreArchivo:.+}")
    public ResponseEntity <Resource> verFoto (@PathVariable String nombreArchivo) throws IOException
    {
        Path carpeta = Paths.get("C:/ArchivosSubidos").toAbsolutePath().normalize();
        Path archivo = carpeta.resolve(nombreArchivo).normalize();

        MediaType tipoArchivo;

        //SE DETECTA EL TIPO DE ARCHIVO GUARDADO EN LA CARPETA
         String contenido = Files.probeContentType(archivo);

        //SE LANZA EXCEPCION SI EL ARCHIVI NO EXISTE
        if(!Files.exists(archivo))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        //SE LANZA EXCEPCION SI EL TIPO DE ARCHIVO NO ES CORRECTO
        if(!nombreArchivo.toLowerCase().endsWith(".jpeg")||
        !(nombreArchivo.toLowerCase().endsWith(".jpg")||
        !(nombreArchivo.toLowerCase().endsWith(".png"))))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

       
        //SI HAY CONTENIDO EN LA CARPERTA
        if(contenido!=null)
        {   

            tipoArchivo = MediaType.parseMediaType(contenido);
       } 
       //SI EL NOMBRE TERMINA CON EL SUFIJO .png 
       else if (nombreArchivo.endsWith(".png"))
       
       {    
            // SE RETORNA UN ARCHIVO PNG
             tipoArchivo = MediaType.IMAGE_PNG;
       } 

        else 
        {   
            //SINO, SE RETORNA UNA IMAGEN JPEG
            tipoArchivo = MediaType.IMAGE_JPEG;
           
        }

         Resource recurso = new UrlResource(archivo.toUri());


        return ResponseEntity.ok().
        contentType(tipoArchivo).
        header(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=\""+
        recurso.getFilename()+ "\"")
        .body(recurso);      

    }
    
    

}
