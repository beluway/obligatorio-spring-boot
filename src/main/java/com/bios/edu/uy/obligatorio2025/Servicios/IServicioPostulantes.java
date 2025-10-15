package com.bios.edu.uy.obligatorio2025.Servicios;

import java.time.LocalDate;
import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioPostulantes {

    void agregar (Postulante postulante) throws Exception;
    void modificar (Postulante postulante) throws Exception;
    void eliminar (String usuario) throws Exception;
    List<Postulante> lista() throws Exception;   
    public Postulante obtener (String usuario) throws Exception;
    Boolean MayorEdad(LocalDate fechaNacimiento)  throws Exception;
    Postulante buscar(String usuario) throws Exception;

}
