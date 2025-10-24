package com.bios.edu.uy.obligatorio2025.utilidades;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class UtilidadesArchivos {

    public static void guardarImagen(byte[]datos,String directorio, String nombreArchivo, String formato)
    throws IOException
    {
        BufferedImage imagen = ImageIO.read(new ByteArrayInputStream(datos));

        File archivo = new File( directorio,nombreArchivo+"."+formato.toLowerCase());

        ImageIO.write(imagen, formato.toLowerCase(), archivo);

    }

    public static void guardarPdf(byte[]datos,String directorio, String nombreArchivo, String formato) throws IOException{
        
    if (!nombreArchivo.toLowerCase().endsWith("." + formato.toLowerCase())) {
        nombreArchivo += "." + formato.toLowerCase();
    }

    File archivo = new File(directorio, nombreArchivo);

    // Usar try-with-resources para cerrar automáticamente el FileOutputStream
    try (FileOutputStream fos = new FileOutputStream(archivo)) {
        fos.write(datos);
        fos.flush();
    }
    }



    public static void main(String rutaArchivo) {
        
       //  File archivo = new File(rutaArchivo);
       //archivo.delete();

        new File(rutaArchivo).delete();        

    }

}
