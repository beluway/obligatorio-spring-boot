package com.bios.edu.uy.obligatorio2025.Servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;
import com.fasterxml.jackson.databind.node.POJONode;



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
    public List<Oferta> listaOfertasVigentesParaPostularse(LocalDate fechaFinPublicacion,Postulante postulante)
    {
      
        List<Oferta> listaOfertasVigentes = repositorioOfertas.findAll(IRepositorioOfertas.ofertasVigentes(fechaFinPublicacion));

        List<Postulacion> listaPostulacionesDelPostulante = repositorioPostulaciones.findAllByPostulante(postulante);
 
        /* //DE TODAS LAS OFERTAS VIGENTES
         for(Oferta o:repositorioOfertas.findAll(IRepositorioOfertas.ofertasVigentes(fechaFinPublicacion)))
         {
            //Y DE TODAS LAS POSTULACIONES (QUE YA TIENEN LAS OFERTAS ELEGIDAS)
            for(Postulacion p:repositorioPostulaciones.findAllByPostulante(postulante))
            {
                // SE FILTRAN LAS OFERTAS QUE NO ESTAN EN LAS POSTULACIONES DEL POSTULANTE
                if(p.getOferta().getId()!=o.getId())
                {
                    //Y SE AGREGAN A LA LISTA DE OFERTAS DISPONIBLES PARA EL POSTULANTE
                    listaOfertasDisponibles_VigentesParaPostularse.add(o);
                }
            }
         } */

         /*    for(Oferta o:listaOfertasVigentes)

                for(Postulacion p:listaPostulacionesDelPostulante)
                {
                    if(!p.getOferta().getId().equals(o.getId()))
                      {
                            listaOfertasDisponibles_VigentesParaPostularse.add(o);
                      }
                } */
            

        List<Oferta> listaOfertasVigentesParaPostularse = listaOfertasVigentes.stream()
                .filter(oferta -> listaPostulacionesDelPostulante.stream().
                noneMatch(postulacion->postulacion.getOferta().getId().equals(oferta.getId())))
                .toList();

         return listaOfertasVigentesParaPostularse;
    }    



}
