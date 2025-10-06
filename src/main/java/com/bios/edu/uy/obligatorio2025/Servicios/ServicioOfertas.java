package com.bios.edu.uy.obligatorio2025.Servicios;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;

import jakarta.validation.OverridesAttribute;


@Service
public class ServicioOfertas implements IServicioOfertas {

@Autowired
private IRepositorioOfertas repositorioOfertas;


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
        try {
            repositorioOfertas.delete(obtener(id));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public List<Oferta> listaOfertas() throws Exception
    {
        //List<ofertas> lista = repositorioOfertas.findAll();
        //prueba en console.log
        //System.out.println("Ofertas encontradas:"+listaOfertas().size());

         return repositorioOfertas.findAll();
    }


    @Override
    public List<Oferta> listaOfertasCliente(Cliente cliente)
    {
        return repositorioOfertas.findAllByCliente(cliente);
    }


   @Override 
   public Oferta obtener(Integer id) throws Exception
   {    

      Oferta ofertaEncontrada =  repositorioOfertas.findById(id).orElse(null);
            

       return ofertaEncontrada;
    } 


    @Override
    public List<Oferta> listaOfertasVigentes(LocalDate fechaFinPublicacion)
    {
         List<Oferta> listaOfertasVigentes = repositorioOfertas.findAll(IRepositorioOfertas.ofertasVigentes(fechaFinPublicacion));
   
         return listaOfertasVigentes;
    }    
 
  


}
