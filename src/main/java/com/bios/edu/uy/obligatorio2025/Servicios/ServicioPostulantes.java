package com.bios.edu.uy.obligatorio2025.Servicios;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Rol;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;


@Service
public class ServicioPostulantes  implements IServicioPostulantes{
    
    //SE INYECTA EL REPOSITORIO POSTULANTES EN EL SERVICIO POSTULANTES
    @Autowired
    private IRepositorioPostulantes respositorioPostulantes;

    
       @Autowired
       PasswordEncoder codificador; 


     @Override 
    public void agregar (Postulante postulante) throws ExcepcionBiosWork
    {
            postulante.setActivo(true);

            Postulante existente = respositorioPostulantes.findById(postulante.getUsuario()).orElse(null);

            if(existente!=null)
            {
                throw new ExcepcionBiosWork("ya existe el postulante");
            }

            postulante.getRoles().add(new Rol("postulante"));
            postulante.setActivo(true);
            postulante.setClave(codificador.encode(postulante.getClave()));

          respositorioPostulantes.save(postulante);
    }

     @Override 
    public void modificar (Postulante postulante) throws ExcepcionBiosWork
    {

        postulante.getRoles().add(new Rol("postulante"));
        postulante.setActivo(true);
        postulante.setClave(codificador.encode(postulante.getClave()));

        respositorioPostulantes.save(postulante);
    }


     @Override 
    public void eliminar (String usuario) throws ExcepcionBiosWork
    {
        respositorioPostulantes.delete(obtener(usuario));
    }

    @Override 
    public Postulante obtener (String usuario) 
    {
        Postulante postulanteEncontrado = respositorioPostulantes.findByUsuario(usuario).orElse(null);

        return postulanteEncontrado;       
    }


    @Override
    public Postulante buscar(String usuario)
    {
           return respositorioPostulantes
            .findByUsuario(usuario)
            .orElse(null);
    }
 


     @Override 
    public List<Postulante> lista() throws Exception
    {
       // ArrayList<postulantes> lista = new ArrayList<>();

       List<Postulante> lista = respositorioPostulantes.findAll();

        return lista;
    }

     @Override
        public Boolean MayorEdad(LocalDate fechaNacimiento) throws Exception
        {
            
            Boolean mayorDeEdad=true;
            
            int cantidadAños = LocalDate.now().getYear() - fechaNacimiento.getYear();

            if(cantidadAños>18)
            {
                  return mayorDeEdad;                 
                
            }

            else if(cantidadAños<=18)
            {
                if(LocalDate.now().getMonth()==fechaNacimiento.getMonth())
                {
                    if(LocalDate.now().getDayOfMonth()>=fechaNacimiento.getDayOfMonth())
                    {
                       return mayorDeEdad;    
                    }

                    return mayorDeEdad=false;
                }  
            }



               return mayorDeEdad=false;
        }

}

