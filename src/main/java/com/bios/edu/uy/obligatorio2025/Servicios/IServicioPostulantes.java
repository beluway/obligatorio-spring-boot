package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;

public interface IServicioPostulantes {

    void agregar (postulantes postulante) throws Exception;
    void modificar (postulantes postulante) throws Exception;
    void eliminar (String usuario) throws Exception;
    List<postulantes> listaPostulante() throws Exception;
    postulantes obtener (String usuario) throws Exception;
}
