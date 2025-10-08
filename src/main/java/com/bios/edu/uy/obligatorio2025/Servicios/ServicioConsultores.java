package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioConsultores;

@Service
public class ServicioConsultores implements IServicioConsultores{
    
    @Autowired
    private IRepositorioConsultores repositorioConsultores;

    
    @Override
    public void agregar (Consultor consultor) throws ExcepcionBiosWork
    {
        repositorioConsultores.save(consultor);
    }


    @Override
    public void modificar(Consultor consultor) throws ExcepcionBiosWork
    {
        try {
            repositorioConsultores.save(obtener(consultor.getUsuario()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @Override
    public void eliminar (String usuario) throws ExcepcionBiosWork
    {
        try {
            repositorioConsultores.delete(obtener(usuario));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @Override
    public List<Consultor>listaConsultores() {
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
