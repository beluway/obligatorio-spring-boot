package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.clientes;


@Service
public class ServicioClientes implements IServicioClientes {

    public void agregar(clientes cliente)
    {

    }
    
    public void modificar (clientes cliente)
    {

    }

    public void eliminar (String usuario)
    {

    }

    public List<clientes>listaClientes()
    {
          ArrayList<clientes> lista = new ArrayList<>();

          return lista;
    }

}
