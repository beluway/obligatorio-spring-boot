package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;

public interface IServicioPostulaciones {

    void agregar (Postulacion postulacion) throws Exception;
    void modificar (Postulacion postulacion) throws Exception;
    void eliminar (Integer id) throws Exception;
    List<Postulacion> listaPostulaciones () throws Exception;
    
}
