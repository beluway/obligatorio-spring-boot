package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionYaExiste;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioAreas;

@Service
public class ServicioAreas implements IServicioAreas{


    @Autowired
    private IRepositorioAreas repositorioAreas;

     
//AGREGAR
    @Override
    public void agregar(Area area) throws ExcepcionBiosWork
    {
        if(repositorioAreas.findByNombre(area.getNombre()).isPresent()){
            throw new ExcepcionYaExiste("El área con ese nombre ya existe.");
        }
            repositorioAreas.save(area);
    }

//ELIMINAR 
 @Override
    public void eliminar (Area area) throws ExcepcionBiosWork
    {
         if (obtener(area.getId())!=null) {
            repositorioAreas.delete(area);
        }
        else{
            throw new ExcepcionNoExiste("El área no existe");
        } 
        
    }

//LISTAR
    //devuelve listado de areas
    @Override
    public List<Area> listaAreas() {

        return repositorioAreas.findAll();

    }

//OBTENER
    //si devuelve null es porque no la encontró
     @Override
    public Area obtener(Integer id) throws ExcepcionBiosWork
     {
        Area areaEncontrada = repositorioAreas.findById(id).orElse(null);

        if (areaEncontrada==null) {
            throw new ExcepcionNoExiste("El área no existe");
        }

        return areaEncontrada;
    
    }

     @Override
     public void modificar(Area area) throws ExcepcionBiosWork
     {

        Area areaEncontrada = repositorioAreas.findById(area.getId()).orElse(null);

        if (areaEncontrada ==null) {
            throw new ExcepcionNoExiste("El área no existe");
        }

        //guardo el area nueva
         repositorioAreas.save(area);
     }


 
 }
