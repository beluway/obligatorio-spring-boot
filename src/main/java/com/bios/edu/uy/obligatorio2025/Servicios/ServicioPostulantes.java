package com.bios.edu.uy.obligatorio2025.Servicios;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Rol;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulaciones;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;


@Service
public class ServicioPostulantes  implements IServicioPostulantes{
    
    //SE INYECTA EL REPOSITORIO POSTULANTES EN EL SERVICIO POSTULANTES
    @Autowired
    private IRepositorioPostulantes respositorioPostulantes;

    @Autowired
    private IRepositorioPostulaciones repositorioPostulaciones;

    @Autowired
    PasswordEncoder codificador; 


    @Autowired
    private HttpServletRequest solicitudCerrarSesionDespuesdeEliminar;

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
    public void modificar (Postulante  nuevo) throws ExcepcionBiosWork
    {

          nuevo.setActivo(true);


          //ESTE ES PARA: SACAR EL ROL, Y LA CONTRASEÑA GUARDADA EN CASO DE QUE NO SE CAMBIE LA CONTRASEÑA
          Postulante existe = respositorioPostulantes.findById(nuevo.getUsuario()).orElse(null);


          if(nuevo.getClave().isEmpty()||nuevo.getClave().isBlank())
          {
            nuevo.setClave(existe.getClave());
          }

          else
          {
             nuevo.setClave(codificador.encode(nuevo.getClave()));
          }

          if(existe==null)
          {
            throw new ExcepcionNoExiste("el postulante no existe");
          }

         nuevo.getRoles().clear();

         for(Rol r : existe.getRoles())
         {
            nuevo.getRoles().add(r);
         }
                  

        respositorioPostulantes.save(nuevo);
    }


    @Override 
    public void eliminar (String usuario) throws ExcepcionBiosWork
    {

        Postulante postualnteBD = respositorioPostulantes.findById(usuario).orElse(null);

        List<Postulacion> postulaciones = repositorioPostulaciones.findAllByPostulante(postualnteBD);

        if(postulaciones.size()>0)
        {
            for(Postulacion P : postulaciones)
            {
                repositorioPostulaciones.delete(P);
            }
        }

        respositorioPostulantes.delete(postualnteBD);

        //SE OBTIENE LA SESION ACTUAL DEL USUARIO PARA BORRAR LOS DATOS DE SESION DESPUES DE ELIMINARLO DE LA BASE DE DATOS
        solicitudCerrarSesionDespuesdeEliminar.getSession().invalidate();
    }



    @Override 
    public Postulante obtener (String usuario) throws ExcepcionBiosWork
    {
        Postulante postulanteEncontrado = respositorioPostulantes.findByUsuario(usuario).orElse(null);

        return postulanteEncontrado;       
    }


    @Override
    public Postulante buscar(String usuario)  throws ExcepcionBiosWork
    {
           return respositorioPostulantes
            .findByUsuario(usuario)
            .orElse(null);
    }
 

     @Override 
    public List<Postulante> lista() throws ExcepcionBiosWork
    {
       // ArrayList<postulantes> lista = new ArrayList<>();

       List<Postulante> lista = respositorioPostulantes.findAll();

        return lista;
    }


     @Override
        public Boolean MayorEdad(LocalDate fechaNacimiento) throws ExcepcionBiosWork
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


     public List<Postulante> buscarPorCriterio(String criterio) {
        return respositorioPostulantes.findAll().stream()
                .filter(p -> p.getUsuario().toLowerCase().contains(criterio.toLowerCase()))
                .toList();
     }


 
     @Override
     @Transactional
     public void actualizarCantidad(String usuario, int cantidad) throws Exception
     {
        respositorioPostulantes.actualizarCantidadPostulaciones(usuario, cantidad);
     }



}

