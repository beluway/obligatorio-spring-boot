package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.areas;

public interface IServicioAreas {
    
    List<areas> listaAreas();
    void agregar(areas area) throws Exception;
    void eliminar (String nombre) throws Exception;
    areas obtener(String nombre);

}
