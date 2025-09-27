package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.consultores;

public interface IServicioConsultores {
    
    void agregar (consultores consultor) throws Exception;
    void modificar(consultores consultor) throws Exception;
    void eliminar (String usuario) throws Exception;
    List<consultores>listaConsultores() throws Exception;
    consultores obtener(String usuario) throws Exception;
}
