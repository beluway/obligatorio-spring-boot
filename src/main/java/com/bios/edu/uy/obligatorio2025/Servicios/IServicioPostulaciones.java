package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.postulaciones;

public interface IServicioPostulaciones {

    void agregar (postulaciones postulacion) throws Exception;
    void modificar (postulaciones postulacion) throws Exception;
    void eliminar (Integer id) throws Exception;
    List<postulaciones> listaPostulaciones () throws Exception;
    
}
