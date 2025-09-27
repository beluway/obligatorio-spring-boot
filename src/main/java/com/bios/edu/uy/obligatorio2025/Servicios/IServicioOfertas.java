package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;

public interface IServicioOfertas {

    void agregar (ofertas oferta) throws Exception;
    void modificar (ofertas oferta) throws Exception;
    void eliminar (Integer codigo) throws Exception;
    List<ofertas> listaOfertas() throws Exception;
    ofertas obtener(Integer id) throws Exception;
}
