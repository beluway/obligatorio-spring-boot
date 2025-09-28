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
        /* if (obtener(area.getNombre())!=null) {
            throw new Exception("El area ya existe");
        }
        else{
            areas.add(area);
        } */


        repositorioAreas.save(area);
        
    }

//ELIMINAR 
 @Override
    public void eliminar (Integer id) throws Exception
    {
       /*  if (obtener(nombre)!=null) {
            areas.remove(obtenerPosicion(nombre));
        }
        else{
            throw new Exception("El area no existe");
        } */

        repositorioAreas.delete(obtener(id));
        
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
    public Area obtener(Integer nombre) {
      /*   areas areaEncontrada = null;

        for(areas a : areas){
            if(a.getNombre()==nombre){
                areaEncontrada=a;
                break;
            }
        }
        return areaEncontrada; */

        Area areaEncontrada = repositorioAreas.findById(nombre).orElse(null);

        return areaEncontrada;
    
    }


 

}
