package com.bios.edu.uy.obligatorio2025.Excepciones;

public class ExcepcionYaExiste extends ExcepcionBiosWork{

    public ExcepcionYaExiste(){}

    public ExcepcionYaExiste(String mensaje){
        super(mensaje);
    }

    public ExcepcionYaExiste(String mensaje, Exception excepcionInterna){
        super(mensaje,excepcionInterna);
    }    

}
