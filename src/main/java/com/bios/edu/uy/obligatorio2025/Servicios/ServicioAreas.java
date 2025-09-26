package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.areas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioAreas;

@Service
public class ServicioAreas implements IServicioAreas{


    @Autowired
    private IRepositorioAreas repositorioAreas;

    private List<areas> areas;

    public ServicioAreas(){

        areas = new ArrayList<>();

       // areas.add(new areas(1, "alguna", true));
    }
    
//AGREGAR
    public void agregar(areas area) throws Exception
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
    public void eliminar (String nombre) throws Exception
    {
       /*  if (obtener(nombre)!=null) {
            areas.remove(obtenerPosicion(nombre));
        }
        else{
            throw new Exception("El area no existe");
        } */

        repositorioAreas.delete(obtener(nombre));
        
    }

//LISTAR
    //devuelve listado de areas
    public List<areas> listaAreas() {

        ArrayList<areas> lista = new ArrayList<>();

        return lista;
    }

//OBTENER
    //si devuelve null es porque no la encontró, mejorar
    public areas obtener(String nombre) {
      /*   areas areaEncontrada = null;

        for(areas a : areas){
            if(a.getNombre()==nombre){
                areaEncontrada=a;
                break;
            }
        }
        return areaEncontrada; */

        areas areaEncontrada = repositorioAreas.findById(nombre).orElse(null);

        return areaEncontrada;
    
    }


 

}
