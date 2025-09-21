package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.consultores;

public interface IServicioConsultores {
    
    void agregar (consultores consultor);
    void modificar(consultores consultor);
    void eliminar (String usuario);
    List<consultores>listaConsultores();
}
