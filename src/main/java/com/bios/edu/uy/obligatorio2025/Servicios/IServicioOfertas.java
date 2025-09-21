package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;

public interface IServicioOfertas {

    void agregar (ofertas oferta);
    void modificar (ofertas oferta);
    void eliminar (Integer codigo);
    List<ofertas> listaOfertas();
    
}
