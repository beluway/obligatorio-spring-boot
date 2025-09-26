package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.consultores;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioConsultores;

@Service
public class ServicioConsultores implements IServicioConsultores{
    
    
    private IRepositorioConsultores repositorioConsultores;

    public void agregar (consultores consultor)
    {
        repositorioConsultores.save(consultor);
    }

    public void modificar(consultores consultor)
    {
        repositorioConsultores.save(obtener(consultor.getUsuario()));
    }

    public void eliminar (String usuario)
    {
        repositorioConsultores.delete(obtener(usuario));
    }

    public List<consultores>listaConsultores()
    {
         //ArrayList<consultores> lista = new ArrayList<>();

        List<consultores> lista = repositorioConsultores.findAll();

         return lista;
    }

    private consultores obtener(String usuario)
    {
        consultores consultorEncontrado = repositorioConsultores.findById(usuario).orElse(null);

        return consultorEncontrado;
    }

}
