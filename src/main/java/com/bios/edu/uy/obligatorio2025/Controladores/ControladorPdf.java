package com.bios.edu.uy.obligatorio2025.Controladores;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ControladorPdf {


   @GetMapping("/pdf/{nombreArchivo:.+}")
public ResponseEntity<Resource> verPdf(@PathVariable String nombreArchivo) throws IOException {

    Path carpeta = Paths.get("C:/ArchivosSubidos").toAbsolutePath().normalize();
    Path archivo = carpeta.resolve(nombreArchivo+".pdf").normalize();

    if (!Files.exists(archivo)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    if (!nombreArchivo.toLowerCase().endsWith(".pdf")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    Resource recurso = new UrlResource(archivo.toUri());



    String tipo = Files.probeContentType(archivo);
    MediaType mediaType = (tipo != null) ? MediaType.parseMediaType(tipo) : MediaType.APPLICATION_PDF;

    return ResponseEntity.ok()
            .contentType(mediaType)
            .body(recurso);



/* 
    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
            .body(recurso); */


}

}
