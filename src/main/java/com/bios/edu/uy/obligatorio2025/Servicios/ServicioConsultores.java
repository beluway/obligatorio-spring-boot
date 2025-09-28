package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioConsultores;

@Service
public class ServicioConsultores implements IServicioConsultores{
    
    @Autowired
    private IRepositorioConsultores repositorioConsultores;

     @Override
    public void agregar (Consultor consultor) throws Exception
    {
        repositorioConsultores.save(consultor);
    }

     @Override
    public void modificar(Consultor consultor) throws Exception
    {
        repositorioConsultores.save(obtener(consultor.getUsuario()));
    }

     @Override
    public void eliminar (String usuario) throws Exception
    {
        repositorioConsultores.delete(obtener(usuario));
    }

     @Override
    public List<Consultor>listaConsultores() throws Exception
    {
         //ArrayList<consultores> lista = new ArrayList<>();

        List<Consultor> lista = repositorioConsultores.findAll();

         return lista;
    }
    

     @Override
    public Consultor obtener(String usuario) throws Exception
    {
        Consultor consultorEncontrado = repositorioConsultores.findById(usuario).orElse(null);

        return consultorEncontrado;
    }

}
