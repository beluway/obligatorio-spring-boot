package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.areas;
import com.bios.edu.uy.obligatorio2025.Dominio.clientes;

@Service
public class ServicioAreas implements IServicioAreas{

    private List<areas> areas;

    public ServicioAreas(){

        areas = new ArrayList<>();

       // areas.add(new areas(1, "alguna", true));
    }
    
//AGREGAR
    public void agregar(areas area) throws Exception
    {
        if (obtener(area.getNombre())!=null) {
            throw new Exception("El area ya existe");
        }
        else{
            areas.add(area);
        }
        
    }
//OBTENER POS
    private int obtenerPosicion(String nombre) {
        int posicion = -1;

        for (int i = 0; i < areas.size(); i++) {
            if (areas.get(i).getNombre() == nombre) {
                posicion = i;
                break;
            }
        }

        return posicion;
    }

//ELIMINAR 
    public void eliminar (String nombre) throws Exception
    {
        if (obtener(nombre)!=null) {
            areas.remove(obtenerPosicion(nombre));
        }
        else{
            throw new Exception("El area no existe");
        }
        
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
        areas areaEncontrada = null;

        for(areas a : areas){
            if(a.getNombre()==nombre){
                areaEncontrada=a;
                break;
            }
        }
        return areaEncontrada;
    }


 

}
