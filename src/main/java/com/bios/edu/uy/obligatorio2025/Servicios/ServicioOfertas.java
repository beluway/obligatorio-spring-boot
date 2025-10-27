package com.bios.edu.uy.obligatorio2025.Servicios;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionYaExiste;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;

import jakarta.transaction.Transactional;




@Service
public class ServicioOfertas implements IServicioOfertas {

@Autowired
private IRepositorioOfertas repositorioOfertas;

@Autowired
private IServicioPostulaciones servicioPostulaciones;

@Autowired 
private IServicioPostulantes servicioPostulantes;

@Autowired
private IRepositorioPostulaciones repositorioPostulaciones;

    @Override
    public void agregar (Oferta oferta) throws ExcepcionBiosWork
    {      
       if (oferta.getFechaCierre().isBefore(LocalDate.now())||oferta.getFechaCierre().isEqual(LocalDate.now()) ) {
        throw new ExcepcionBiosWork("La fecha de cierre debe ser posterior a hoy.");
       }

        repositorioOfertas.save(oferta);

    }

    @Override
    public void modificar (Oferta oferta) throws ExcepcionBiosWork
    {
        Oferta ofertaExiste = obtener(oferta.getId());

        if (ofertaExiste==null) {
            throw new ExcepcionNoExiste("La oferta no existe.");
        }

        repositorioOfertas.save(ofertaExiste);


    }
    

    @Override
   /*  @Transactional */
    public void eliminar (Integer id) throws ExcepcionBiosWork
    {
        try 
        {
             Oferta ofertaExiste = obtener(id);

        if (ofertaExiste==null) {
            throw new ExcepcionNoExiste("La oferta no existe.");
        }

            List<Postulacion> listaPostulaciones = repositorioPostulaciones.findAllByOferta(ofertaExiste);

            List<Postulante> listaPostulantes = servicioPostulaciones.obtenerPostulantesPorOferta(ofertaExiste.getId());  

        
        for(Postulante P: listaPostulantes)
         {
          
            int cantidadActualPostulaciones = servicioPostulantes.obtenerCantidad(P.getUsuario())-1;

                //SE ACTUALIZA LA CANTIDAD DE POSTULACIONES DEL POSTULANTE
                servicioPostulantes.actualizarCantidad(P.getUsuario(), cantidadActualPostulaciones);
         }



            for(Postulacion P:listaPostulaciones)
            {
                //SE ELIMINAN LAS POSTULACIONES DE LA OFERTA
                repositorioPostulaciones.delete(P);
            }



            repositorioOfertas.delete(obtener(id));
        }
        catch (Exception e) 
        {
         throw new ExcepcionBiosWork("Hubo un error "+e.getMessage());
        }
    }




    @Override
    public List<Oferta> listaOfertas() 
    {
        return repositorioOfertas.findAll();
    }


    @Override
    public List<Oferta> listaOfertasCliente(Cliente cliente) 
    {
        return repositorioOfertas.findAllByCliente(cliente);
    }

    @Override 
    public Oferta obtener(Integer id) 
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
    //filtra por título o descripción que contenga el criterio de búsqueda
    @Override
    public List<Oferta> buscarPorCriterio(String criterio)  {
        return repositorioOfertas.findAll().stream()
                 .filter(p -> p.getTitulo().toLowerCase().contains(criterio.toLowerCase()) ||
                                p.getDescripcion().toLowerCase().contains(criterio.toLowerCase()))
                 .toList();
    } 
    
}
