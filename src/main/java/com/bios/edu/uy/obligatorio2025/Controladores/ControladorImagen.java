package com.bios.edu.uy.obligatorio2025.Controladores;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ControladorImagen {

    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<Resource> verImagen(@PathVariable String nombre) throws IOException {

        Path carpeta = Paths.get("C:/ArchivosSubidos").toAbsolutePath().normalize();

        // Buscar archivo con extensiones posibles
        Path archivo = null;
        String[] extensiones = {".jpeg", ".jpg", ".png"};

        for (String ext : extensiones) {
            Path posible = carpeta.resolve(nombre + ext).normalize();
            if (Files.exists(posible)) {
                archivo = posible;
                break;
            }
        }

        if (archivo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Resource recurso = new UrlResource(archivo.toUri());

        // Detectar tipo MIME de la imagen
        String tipo = Files.probeContentType(archivo);
        MediaType mediaType = (tipo != null) ? MediaType.parseMediaType(tipo) : MediaType.APPLICATION_OCTET_STREAM;

        return ResponseEntity.ok()
                .contentType(mediaType)
                .body(recurso);
    }
}