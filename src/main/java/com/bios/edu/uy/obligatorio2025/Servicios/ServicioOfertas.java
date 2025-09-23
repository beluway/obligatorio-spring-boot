package com.bios.edu.uy.obligatorio2025.Servicios;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;


@Service
public class ServicioOfertas implements IServicioOfertas {

    private IServicioAreas servicioAreas;
    private IServicioClientes servicioClientes;
    private List<ofertas> ofertas;

    public ServicioOfertas(IServicioAreas servicioAreas, IServicioClientes servicioClientes){

        this.servicioAreas = servicioAreas;

        ofertas = new ArrayList<>();

        ofertas.add(new ofertas(1, Date.valueOf(LocalDate.now()), Date.valueOf("20-10-2025"), servicioClientes.obtener("user1"), "Tu tiene que tlabajal", "Trabajo1", servicioAreas.obtener("alguna"), 10));
    }
    
    public void agregar (ofertas oferta) throws Exception
    {
        if(obtener(oferta.getId())!=null){
            throw new Exception("La oferta ya existe");
        }

        oferta.setArea(servicioAreas.obtener(oferta.getArea().getNombre()));
        oferta.setCliente(servicioClientes.obtener(oferta.getCliente().getUsuario()));

        ofertas.add(oferta);
    }

    public void modificar (ofertas oferta)
    {

    }

    public void eliminar (Integer id)
    {

    }

    public List<ofertas> listaOfertas()
    {
         ArrayList<ofertas> lista = new ArrayList<>();

         return lista;
    }

    public ofertas obtener(Integer id){
        ofertas ofertaEncontrada = null;

        for(ofertas o : ofertas){
            if(o.getId()==id){
                ofertaEncontrada=o;
                break;
            }
        }
        return ofertaEncontrada;
        
    }

}
