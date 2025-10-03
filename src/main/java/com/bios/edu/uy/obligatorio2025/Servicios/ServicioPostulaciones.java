package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;



@Service
public class ServicioPostulaciones implements IServicioPostulaciones{
    
    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;

  
    @Override 
    public void agregar (Postulacion postulacion) throws ExcepcionBiosWork
    {
        repositorioPostulaciones.save(postulacion);
    }

    @Override 
    public void modificar (Postulacion postulacion)throws ExcepcionBiosWork
    {

    }

    @Override 
    public void eliminar (Postulacion postulacion)throws ExcepcionBiosWork
    {
        
    }

    @Override 
    public List<Postulacion> listaPostulaciones ()
    {
        ArrayList<Postulacion> lista = new ArrayList<>();

        return lista;
    }

}
