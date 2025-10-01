package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioConsultores {
    
    void agregar (Consultor consultor) throws ExcepcionBiosWork;
    void modificar(Consultor consultor) throws ExcepcionBiosWork;
    void eliminar (String usuario) throws ExcepcionBiosWork;
    List<Consultor>listaConsultores() throws Exception;
    Consultor obtener(String usuario) throws Exception;
}
