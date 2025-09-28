package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;


import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;

public interface IServicioClientes {
    
    void agregar(Cliente cliente) throws Exception;
    void modificar (Cliente cliente) throws Exception;
    void eliminar (String usuario) throws Exception;
    Cliente obtener (String usuario) throws Exception;
    List<Cliente>listaClientes() throws Exception;


    //////CON PAGINACIÓN
   
  // org.springframework.data.domain.Page <clientes> listar (Pageable pageable);


}
