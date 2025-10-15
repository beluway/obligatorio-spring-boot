package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;


import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioClientes {

    void agregar(Cliente cliente) throws ExcepcionBiosWork;
    void modificar (Cliente cliente, String nuevaClave) throws ExcepcionBiosWork;
    void eliminar (Cliente usuario) throws ExcepcionBiosWork;
    Cliente obtener (String usuario) throws Exception;
    List<Cliente>listaClientes() throws ExcepcionBiosWork;
    List<Cliente> listarActivos() throws ExcepcionBiosWork;
    boolean existePorUrl(String url);

    //////CON PAGINACIÓN

  // org.springframework.data.domain.Page <clientes> listar (Pageable pageable);


}