package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;

public interface IServicioPostulantes {

    void agregar (postulantes postulante);
    void modificar (postulantes postulante);
    void eliminar (String usuario);
    List<postulantes> listaPostulante();
}
