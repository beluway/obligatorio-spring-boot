package com.bios.edu.uy.obligatorio2025.Servicios;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;

import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;




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
            List<Postulacion> listaPostulaciones = respositorioPostulaciones.findByOferta_Id(id);

            for(Postulacion P:listaPostulaciones)
            {
                //SE ELIMINAN LAS POSTULACIONES DE LA OFERTA
                respositorioPostulaciones.delete(P);
            }

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
    public List<Oferta> listaOfertas() throws ExcepcionBiosWork
     {
        return repositorioOfertas.findAll();
    }


    @Override
    public List<Oferta> listaOfertasCliente(Cliente cliente) throws ExcepcionBiosWork
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
    public Oferta obtener(Integer id) throws ExcepcionBiosWork
    {  

      Oferta ofertaEncontrada =  repositorioOfertas.findById(id).orElse(null);         

       return ofertaEncontrada;
    } 


    @Override
    public List<Oferta> listaOfertasVigentes() throws ExcepcionBiosWork
    {
         List<Oferta> listaOfertasVigentes = repositorioOfertas.findAll(IRepositorioOfertas.ofertasVigentes());
   
         return listaOfertasVigentes;
    }

    @Override
    public List<Oferta> buscarPorCriterio(String criterio) throws ExcepcionBiosWork {
        return repositorioOfertas.findAll().stream()
                 .filter(p -> p.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                                p.getDescripcion().toLowerCase().contains(criterio.toLowerCase()))
                 .toList();
    } 
    
    
          

}
