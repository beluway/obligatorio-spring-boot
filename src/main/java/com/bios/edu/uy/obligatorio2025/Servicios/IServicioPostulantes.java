package com.bios.edu.uy.obligatorio2025.Servicios;

import java.time.LocalDate;
import java.util.List;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioPostulantes {

    void agregar (Postulante postulante) throws ExcepcionBiosWork;
    void modificar (Postulante postulante) throws ExcepcionBiosWork;
    void eliminar (String usuario) throws ExcepcionBiosWork;
    List<Postulante> lista() throws ExcepcionBiosWork;
    Boolean MayorEdad(LocalDate fechaNacimiento)  throws ExcepcionBiosWork;
    Postulante obtener(String usuario) throws ExcepcionBiosWork;
    Postulante buscar(String usuario) throws Exception;
    List<Postulante> buscarPorCriterio(String criterio) throws ExcepcionBiosWork;
    void actualizarCantidad(String usuario, int cantidad) throws ExcepcionBiosWork;
      public Integer obtenerCantidad(String usuario) throws ExcepcionBiosWork;

}
