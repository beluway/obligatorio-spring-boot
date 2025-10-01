package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;


@Service
public class ServicioPostulantes  implements IServicioPostulantes{
    
    //SE INYECTA EL REPOSITORIO POSTULANTES EN EL SERVICIO POSTULANTES
    @Autowired
    private IRepositorioPostulantes respositorioPostulantes;


     @Override 
    public void agregar (Postulante postulante) throws Exception
    {
          respositorioPostulantes.save(postulante);
    }

     @Override 
    public void modificar (Postulante postulante) throws Exception
    {
        respositorioPostulantes.save(postulante);
    }


     @Override 
    public void eliminar (String usuario) throws Exception
    {
        respositorioPostulantes.delete(obtener(usuario));
    }

    @Override 
    public Postulante obtener (String usuario) throws Exception
    {
        Postulante postulanteEncontrado = respositorioPostulantes.findById(usuario).orElse(null);

       /*  for(ofertas o : ofertas){
            if(o.getId()==id){
                ofertaEncontrada=o;
                break;
            }
        } */

       return postulanteEncontrado;
       
    }

     @Override 
    public List<Postulante> lista() throws Exception
    {
       // ArrayList<postulantes> lista = new ArrayList<>();

       List<Postulante> lista = respositorioPostulantes.findAll();

        return lista;
    }
}

