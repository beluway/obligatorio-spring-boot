package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.clientes;

public interface IServicioClientes {
    
    void agregar(clientes cliente) throws Exception;
    void modificar (clientes cliente) throws Exception;
    void eliminar (String usuario) throws Exception;
    clientes obtener (String usuario);
    List<clientes>listaClientes();
}
