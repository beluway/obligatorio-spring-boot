package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioPostulantes {

    void agregar (Postulante postulante) throws ExcepcionBiosWork;
    void modificar (Postulante postulante) throws ExcepcionBiosWork;
    void eliminar (String usuario) throws ExcepcionBiosWork;
    List<Postulante> listaPostulante() throws Exception;
    Postulante obtener (String usuario) throws Exception;
}
