package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.postulaciones;
import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;



@Service
public class ServicioPostulaciones implements IServicioPostulaciones{
    
    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;

  

    


    public void agregar (postulaciones postulacion)
    {
        repositorioPostulaciones.save(postulacion);
    }


    public void modificar (postulaciones postulacion)
    {

    }

    public void eliminar (Integer id)
    {
        
    }

    public List<postulaciones> listaPostulaciones ()
    {
        ArrayList<postulaciones> lista = new ArrayList<>();

        return lista;
    }

}
