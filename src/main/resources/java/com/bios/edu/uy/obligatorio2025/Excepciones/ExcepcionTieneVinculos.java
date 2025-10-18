package com.bios.edu.uy.obligatorio2025.Excepciones;

public class ExcepcionTieneVinculos extends ExcepcionBiosWork{
    
    public ExcepcionTieneVinculos(){}

    public ExcepcionTieneVinculos(String mensaje){
        super(mensaje);
    }

    public ExcepcionTieneVinculos(String mensaje, Exception excepcionInterna){
        super(mensaje,excepcionInterna);
    }
}
