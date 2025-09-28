package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;

public interface IServicioConsultores {
    
    void agregar (Consultor consultor) throws Exception;
    void modificar(Consultor consultor) throws Exception;
    void eliminar (String usuario) throws Exception;
    List<Consultor>listaConsultores() throws Exception;
    Consultor obtener(String usuario) throws Exception;
}
