package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioAreas;

@Service
public class ServicioAreas implements IServicioAreas{


    @Autowired
    private IRepositorioAreas repositorioAreas;

     
//AGREGAR
    @Override
    public void agregar(Area area) throws Exception
    {
        /*  if (obtener(area.getId())!=null) {
            throw new Exception("El area ya existe");
        }

        else
        { */
       
            repositorioAreas.save(area);

       /*  } */

       
        
    }

//ELIMINAR 
 @Override
    public void eliminar (Area area) throws Exception
    {
         if (obtener(area.getId())!=null) {
            repositorioAreas.delete(area);
        }
        else{
            throw new Exception("El area no existe");
        } 
        
    }

//LISTAR
    //devuelve listado de areas
    @Override
    public List<Area> listaAreas() {

        return repositorioAreas.findAll();

    }

//OBTENER
    //si devuelve null es porque no la encontró, mejorar
     @Override
    public Area obtener(Integer id) {
      /*   areas areaEncontrada = null;

        for(areas a : areas){
            if(a.getNombre()==nombre){
                areaEncontrada=a;
                break;
            }
        }
        return areaEncontrada; */

        Area areaEncontrada = repositorioAreas.findById(id).orElse(null);

        return areaEncontrada;
    
    }

     @Override
     public void modificar(Area area) throws Exception {

        Area areaEncontrada = repositorioAreas.findById(area.getId()).orElse(null);

        if (areaEncontrada ==null) {
            throw new Exception("El área no existe");
        }

        //guardo el producto 
        repositorioAreas.save(area);
     }


 

}
