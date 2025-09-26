package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;


@Service
public class ServicioPostulantes  implements IServicioPostulantes{
    
    //SE INYECTA EL REPOSITORIO POSTULANTES EN EL SERVICIO POSTULANTES
    @Autowired
    private IRepositorioPostulantes respositorioPostulantes;


    public void agregar (postulantes postulante)
    {
            respositorioPostulantes.save(postulante);
    }

    public void modificar (postulantes postulante)
    {
    
    }

    public void eliminar (String usuario)
    {
        respositorioPostulantes.delete(obtener(usuario));
    }


    public postulantes obtener (String usuario)
    {
        return respositorioPostulantes.findById(usuario);
    }

    public List<postulantes> listaPostulante()
    {
        ArrayList<postulantes> lista = new ArrayList<>();

        return lista;
    }
}
