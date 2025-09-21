package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.postulaciones;

public interface IServicioPostulaciones {

    void crear (postulaciones postulacion);
    void modificar (postulaciones postulacion);
    void eliminar (Integer id);
    List<postulaciones> listaPostulaciones ();
    
}
