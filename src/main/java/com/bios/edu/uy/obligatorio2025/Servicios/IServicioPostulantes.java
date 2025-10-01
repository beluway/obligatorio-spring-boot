package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;

public interface IServicioPostulantes {

    void agregar (Postulante postulante) throws Exception;
    void modificar (Postulante postulante) throws Exception;
    void eliminar (String usuario) throws Exception;
    List<Postulante> lista() throws Exception;
    Postulante obtener (String usuario) throws Exception;
}
