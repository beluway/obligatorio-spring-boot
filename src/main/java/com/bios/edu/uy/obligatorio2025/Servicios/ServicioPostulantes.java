package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.postulantes;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;


@Service
public class ServicioPostulantes  implements IServicioPostulantes{
    
    //SE INYECTA EL REPOSITORIO POSTULANTES EN EL SERVICIO POSTULANTES
    @Autowired
    private IRepositorioPostulantes respositorioPostulantes;


     @Override 
    public void agregar (postulantes postulante) throws Exception
    {
          respositorioPostulantes.save(postulante);
    }

     @Override 
    public void modificar (postulantes postulante) throws Exception
    {
        respositorioPostulantes.save(postulante);
    }


     @Override 
    public void eliminar (String usuario) throws Exception
    {
        respositorioPostulantes.delete(obtener(usuario));
    }

    @Override 
    public postulantes obtener (String usuario) throws Exception
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

     @Override 
    public List<postulantes> listaPostulante() throws Exception
    {
       // ArrayList<postulantes> lista = new ArrayList<>();

       List<postulantes> lista = respositorioPostulantes.findAll();

        return lista;
    }
}

