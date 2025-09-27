package com.bios.edu.uy.obligatorio2025.Servicios;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;


@Service
public class ServicioOfertas implements IServicioOfertas {

@Autowired
private IRepositorioOfertas repositorioOfertas;


    @Override
    public void agregar (ofertas oferta) throws Exception
    {

        // ACA ES LO PREVIO AL ALTA
   /*      if(obtener(oferta.getId())!=null){
            throw new Exception("La oferta ya existe");
        }

        oferta.setArea(servicioAreas.obtener(oferta.getArea().getNombre()));
        oferta.setCliente(servicioClientes.obtener(oferta.getCliente().getUsuario()));

        ofertas.add(oferta); */

        repositorioOfertas.save(oferta);

    }

     @Override
    public void modificar (ofertas oferta) throws Exception
    {
        repositorioOfertas.save(oferta);
    }

     @Override
    public void eliminar (Integer id) throws Exception
    {
        repositorioOfertas.delete(obtener(id));
    }

     @Override
    public List<ofertas> listaOfertas() throws Exception
    {
        //List<ofertas> lista = repositorioOfertas.findAll();
        //prueba en console.log
        //System.out.println("Ofertas encontradas:"+listaOfertas().size());

         return repositorioOfertas.findAll();
    }

     @Override 
   public ofertas obtener(Integer id) throws Exception
   {    

      ofertas ofertaEncontrada =  repositorioOfertas.findById(id).orElse(null);

       /*  for(ofertas o : ofertas){
            if(o.getId()==id){
                ofertaEncontrada=o;
                break;
            }
        } */
       // return ofertaEncontrada;
        

       return ofertaEncontrada;
    } 

}
