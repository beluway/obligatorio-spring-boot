package com.bios.edu.uy.obligatorio2025.Excepciones;

//clase de excepciones general
public class ExcepcionBiosWork extends Exception{
    //constructor x defecto
public ExcepcionBiosWork(){

}

//recibe un mensaje y lo retorna
public ExcepcionBiosWork(String mensaje){
    super(mensaje);
}

//muestra mensaje y tipo de excepcion
public ExcepcionBiosWork(String mensaje, Exception excepcionInterna){
    super(mensaje,excepcionInterna);
}

}
