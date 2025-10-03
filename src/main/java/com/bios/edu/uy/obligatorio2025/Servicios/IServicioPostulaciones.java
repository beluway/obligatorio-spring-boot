package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioPostulaciones {

    void agregar (Postulacion postulacion) throws ExcepcionBiosWork;
    void modificar (Postulacion postulacion) throws ExcepcionBiosWork;
    void eliminar (Postulacion postulacion) throws ExcepcionBiosWork;
    List<Postulacion> listaPostulaciones () throws Exception;
    
}
