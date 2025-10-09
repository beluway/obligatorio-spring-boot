package com.bios.edu.uy.obligatorio2025.Servicios;

import java.rmi.server.ExportException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioPostulaciones {

    void agregar (Postulacion postulacion) throws ExcepcionBiosWork;
    void modificar (Postulacion postulacion) throws ExcepcionBiosWork;
    void eliminar (Postulacion postulacion) throws ExcepcionBiosWork;
    List<Postulacion> listaPostulaciones () throws Exception;  
    List<Postulacion> listaPostulacionesPorPostulante(Postulante postulante) throws Exception;
    public List<Oferta> listaOfertasVigentesParaPostularse(Postulante postulante) throws Exception;
    public Optional<Postulacion> obtener(Integer idOferta, String usuario) throws Exception;
    List<Postulacion> listaPostulacionesPorOferta (Oferta oferta) throws Exception;
    void eliminarConOferta (Oferta oferta) throws Exception;
    void eliminarConPostulante (Postulante postulante) throws Exception;
    Boolean MayorEdad(LocalDate fechaNacimiento)  throws Exception;
}
