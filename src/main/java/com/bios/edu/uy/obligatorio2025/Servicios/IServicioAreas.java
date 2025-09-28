package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;

public interface IServicioAreas {
    
    List<Area> listaAreas();
    void agregar(Area area) throws Exception;
    void eliminar (Area area) throws Exception;
    void modificar (Area area) throws Exception;
    Area obtener(Integer id) throws Exception;;

}
