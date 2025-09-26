package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;
import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;


@Service
public class ServicioPostulantes  implements IServicioPostulantes{
    
    //SE INYECTA EL REPOSITORIO POSTULANTES EN EL SERVICIO POSTULANTES
    @Autowired
    private IRepositorioPostulantes respositorioPostulantes;


    public void agregar (postulantes postulante)
    {
          respositorioPostulantes.save(postulante);
    }

    public void modificar (postulantes postulante)
    {
        respositorioPostulantes.save(postulante);
    }

    public void eliminar (String usuario)
    {
        respositorioPostulantes.delete(obtener(usuario));
    }


    public postulantes obtener (String usuario)
    {
        postulantes postulanteEncontrado = respositorioPostulantes.findById(usuario).orElse(null);

       /*  for(ofertas o : ofertas){
            if(o.getId()==id){
                ofertaEncontrada=o;
                break;
            }
        } */

       return postulanteEncontrado;
       
    }

    public List<postulantes> listaPostulante()
    {
       // ArrayList<postulantes> lista = new ArrayList<>();

       List<postulantes> lista = respositorioPostulantes.findAll();

        return lista;
    }
}

