package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioAreas {
    
    List<Area> listaAreas();
    void agregar(Area area) throws ExcepcionBiosWork;
    void eliminar (Area area) throws ExcepcionBiosWork;
    void modificar (Area area) throws ExcepcionBiosWork;
    Area obtener(Integer id) throws Exception;
    List<Area> buscarPorCriterio(String criterio);

}
