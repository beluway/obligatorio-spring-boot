package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.areas;

public interface IServicioAreas {
    
    List<areas> listaAreas();
    void agregar(areas area);

}
