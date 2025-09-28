package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;

public interface IServicioOfertas {

    void agregar (Oferta oferta) throws Exception;
    void modificar (Oferta oferta) throws Exception;
    void eliminar (Integer codigo) throws Exception;
    List<Oferta> listaOfertas() throws Exception;
    Oferta obtener(Integer id) throws Exception;
}
