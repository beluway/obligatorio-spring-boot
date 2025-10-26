package com.bios.edu.uy.obligatorio2025.Servicios;


import java.util.List;
import java.util.Optional;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioPostulaciones {

    void agregar (Postulacion postulacion) throws ExcepcionBiosWork;
    void eliminar (Postulacion postulacion) throws ExcepcionBiosWork;
    List<Postulacion> listaPostulaciones () throws ExcepcionBiosWork;  
    List<Postulacion> listaPostulacionesPorPostulante(Postulante postulante) throws ExcepcionBiosWork;
    public List<Oferta> listaOfertasVigentesParaPostularse(Postulante postulante) throws ExcepcionBiosWork;
    public Optional<Postulacion> obtener(Integer idOferta, String usuario) throws ExcepcionBiosWork;
    void eliminarConOferta (Oferta oferta) throws ExcepcionBiosWork;
    void eliminarConPostulante (Postulante postulante) throws ExcepcionBiosWork;
     List<Postulante> obtenerPostulantesPorOferta(Integer idOferta) throws ExcepcionBiosWork;
     List<Postulacion> buscarPorCriterio(String criterio);
   
   
   
}
