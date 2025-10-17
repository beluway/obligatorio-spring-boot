package com.bios.edu.uy.obligatorio2025.Servicios;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;

import jakarta.validation.OverridesAttribute;


@Service
public class ServicioOfertas implements IServicioOfertas {

@Autowired
private IRepositorioOfertas repositorioOfertas;

@Autowired
private IRepositorioPostulaciones respositorioPostulaciones;



    @Override
    public void agregar (Oferta oferta) throws ExcepcionBiosWork
    {      

        repositorioOfertas.save(oferta);

    }

    @Override
    public void modificar (Oferta oferta) throws ExcepcionBiosWork
    {
        repositorioOfertas.save(oferta);
    }

    
    @Override
    public void eliminar (Integer id) throws ExcepcionBiosWork
    {
        try 
        {
            repositorioOfertas.delete(obtener(id));
        }
        catch (Exception e) 
        {
            // TODO Auto-generated catch block
          e.printStackTrace();
        }
    }

   /*  List<Oferta> findAllByPostulante(Postulante postulante)
      {
        List<Oferta>ofertasXPostulante = new List<Oferta>();

        if(Postulacion p:repositorioPostulaciones.findByPostuante(postulante.getUsuario()))
        {
            ofertasXPostulante.add(p.getOferta());
        }

        return ofertasXPostulante;
        }  */



    @Override
    public List<Oferta> listaOfertas() throws Exception
    {
        return repositorioOfertas.findAll();
    }


    @Override
    public List<Oferta> listaOfertasCliente(Cliente cliente) throws Exception
    {
        return repositorioOfertas.findAllByCliente(cliente);
    }

/* 
    @Override
    public List<Oferta> listaOfertasPostulante( Postulante postulante) throws Exception
    {
          return repositorioOfertas.findAllByPostulante(postulante);
    } */


    @Override 
    public Oferta obtener(Integer id) throws Exception
    {  

      Oferta ofertaEncontrada =  repositorioOfertas.findById(id).orElse(null);         

       return ofertaEncontrada;
    } 


    @Override
    public List<Oferta> listaOfertasVigentes()
    {
         List<Oferta> listaOfertasVigentes = repositorioOfertas.findAll(IRepositorioOfertas.ofertasVigentes());
   
         return listaOfertasVigentes;
    }

    @Override
    public List<Oferta> buscarPorCriterio(String criterio) {
        return repositorioOfertas.findAll().stream()
                 .filter(p -> p.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                                p.getDescripcion().toLowerCase().contains(criterio.toLowerCase()))
                 .toList();
    } 
    
    
          

}
