package com.bios.edu.uy.obligatorio2025.Servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion.PostulacionId;
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
        repositorioPostulaciones.delete(postulacion);
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

 /*   @Override
   public Postulacion obtener(Postulacion postulacion)
   {
      /*  PostulacionId id = new PostulacionId(postulacion.getPostulante().getUsuario(),postulacion.getOferta().getId()); */

/*          PostulacionId id = postulacion.getId();        

       return  repositorioPostulaciones.findById(id.hashCode()); */



/* 
PostulacionId id = new PostulacionId(null, null);
MiEntidad entidad = repositorio.findById(id).orElse(null);
@Autowired
private PostulacionRepository postulacionRepository;

public Postulacion obtenerPostulacion(String usuarioPostulante, Integer idOferta) {

    // Crear el objeto de clave compuesta
    Postulacion.PostulacionId id = new Postulacion.PostulacionId();
    id.setUsuarioPostulante(usuarioPostulante);
    id.setIdOferta(idOferta);

    // Buscar en la base de datos
    return postulacionRepository.findById(id).orElse(null);
} */

/* 
@Query(value = "DELETE postulaciones WHERE ") */


    @Override
    public Optional<Postulacion> obtener(Postulacion postulacion)
    {
        return  repositorioPostulaciones.findByOfertaAndPostulante(postulacion.getOferta(),postulacion.getPostulante());
    }

}
