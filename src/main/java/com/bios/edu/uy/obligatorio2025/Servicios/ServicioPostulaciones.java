package com.bios.edu.uy.obligatorio2025.Servicios;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;

import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;




@Service
public class ServicioPostulaciones implements IServicioPostulaciones{
    
    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;

    @Autowired
    private IRepositorioOfertas repositorioOfertas;
  

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
        repositorioPostulaciones.delete(postulacion);
    }


    @Override 
    public void eliminarConOferta (Oferta oferta)throws ExcepcionBiosWork
    {        
        for(Postulacion p: repositorioPostulaciones.findAllByOferta(oferta))
        {
            repositorioPostulaciones.delete(p);
        }        
    } 


    @Override 
    public void eliminarConPostulante (Postulante postulante)throws ExcepcionBiosWork
    {        
        for(Postulacion p: repositorioPostulaciones.findAllByPostulante(postulante))
        {
            repositorioPostulaciones.delete(p);
        }        
    } 


    @Override 
    public List<Postulacion> listaPostulaciones ()
    {
        ArrayList<Postulacion> lista = new ArrayList<>();

        return lista;
    }

    
    @Override
    public List<Postulacion> listaPostulacionesPorPostulante(Postulante postulante)
    {
       return repositorioPostulaciones.findAllByPostulante(postulante);
    }


    @Override
    public List<Oferta> listaOfertasVigentesParaPostularse(Postulante postulante)
    {      
        List<Oferta> listaOfertasVigentes = repositorioOfertas.findAll(IRepositorioOfertas.ofertasVigentes());

        List<Postulacion> listaPostulacionesDelPostulante = repositorioPostulaciones.findAllByPostulante(postulante);
                
        List<Oferta> listaOfertasVigentesParaPostularse = listaOfertasVigentes.stream()
                .filter(oferta -> listaPostulacionesDelPostulante.stream().
                noneMatch(postulacion->postulacion.getOferta().getId().equals(oferta.getId())))
                .toList();

         return listaOfertasVigentesParaPostularse;
    }    

    @Override
    public Optional<Postulacion> obtener(Integer idOferta, String usuario)
    {
        return  repositorioPostulaciones.findById_IdOfertaAndId_UsuarioPostulante(idOferta,usuario);
    }


    // SE SACAN TODAS LAS POSTULACIONES POR OFERTA
    //PARA HACER LA BAJA FISICA, PRIMERO SE ELIMINAN TODAS ESTAS POSTULACIONES, Y DESPUES LA OFERTA
    @Override 
    public List<Postulacion> listaPostulacionesPorOferta (Oferta oferta)
    {
        return  repositorioPostulaciones.findAllByOferta(oferta);
    }

       
}
