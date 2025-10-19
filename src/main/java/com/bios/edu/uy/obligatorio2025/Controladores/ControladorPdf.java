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
public class ControladorPdf {

    @Autowired
    private ServicioPostulantes servicioPostulante;

   @GetMapping("/pdf/{nombreArchivo:.+}")
public ResponseEntity<Resource> verPdf(@PathVariable String nombreArchivo) throws IOException {

    Path carpeta = Paths.get("C:/ArchivosSubidos").toAbsolutePath().normalize();
    Path archivo = carpeta.resolve(nombreArchivo).normalize();

    if (!Files.exists(archivo)) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    if (!nombreArchivo.toLowerCase().endsWith(".pdf")) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    Resource recurso = new UrlResource(archivo.toUri());

    return ResponseEntity.ok()
            .contentType(MediaType.APPLICATION_PDF)
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
            .body(recurso);
}

}
