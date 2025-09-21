package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.clientes;

public interface IServicioClientes {
    
    void agregar(clientes cliente);
    void modificar (clientes cliente);
    void eliminar (String usuario);
    List<clientes>listaClientes();
}
