package com.bios.edu.uy.obligatorio2025.Servicios;
import java.time.LocalDate;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.SetOptions.Propagation;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Rol;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionYaExiste;
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
                throw new ExcepcionYaExiste("ya existe el postulante");
            }

         

            if(MayorEdad(postulante.getFechanacimiento())==true)
            {        
      
                postulante.getRoles().add(new Rol("postulante"));
             
                postulante.setClave(codificador.encode(postulante.getClave()));

                respositorioPostulantes.save(postulante);
            }
           
            else
            {
              throw new ExcepcionBiosWork("El postulante debe ser mayor de edad");
            }

    }
    
 


    @Override 
    public void modificar (Postulante  nuevo) throws ExcepcionBiosWork
    {

          nuevo.setActivo(true);


          //ESTE ES PARA: SACAR EL ROL, Y LA CONTRASEÑA GUARDADA EN CASO DE QUE NO SE CAMBIE LA CONTRASEÑA
          Postulante existe = respositorioPostulantes.findById(nuevo.getUsuario()).orElse(null);


        if(MayorEdad(nuevo.getFechanacimiento())==true)
        {

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
            throw new ExcepcionNoExiste("No existe el postulante");
          }

         nuevo.getRoles().clear();

         for(Rol r : existe.getRoles())
         {
            nuevo.getRoles().add(r);
         }


        respositorioPostulantes.save(nuevo);
        }

        else
        {
            throw new ExcepcionBiosWork("El postulante debe ser mayor de edad");
        }


    }


    @Override 
    public void eliminar (String usuario) throws ExcepcionBiosWork
    {

        Postulante postualnteBD = respositorioPostulantes.findById(usuario).orElse(null);

        if (postualnteBD==null) {
            throw new ExcepcionNoExiste("No existe el postulante.");
        }

        List<Postulacion> postulaciones = repositorioPostulaciones.findAllByPostulante(postualnteBD);
        //se borran sus postulaciones
        if(postulaciones.size()>0)
        {
            for(Postulacion P : postulaciones)
            {
                repositorioPostulaciones.delete(P);
            }
        }
        //se borra definitivamente el postulante de la base de datos
        respositorioPostulantes.delete(postualnteBD);

        //SE OBTIENE LA SESION ACTUAL DEL USUARIO PARA BORRAR LOS DATOS DE SESION DESPUES DE ELIMINARLO DE LA BASE DE DATOS
        solicitudCerrarSesionDespuesdeEliminar.getSession().invalidate();
    }


     @Override 
    public List<Postulante> lista() throws ExcepcionBiosWork
    {
       // ArrayList<postulantes> lista = new ArrayList<>();

       List<Postulante> lista = respositorioPostulantes.findAll();

        return lista;
    }


    @Override
public Boolean MayorEdad(LocalDate fechaNacimiento) throws ExcepcionBiosWork {

    if (fechaNacimiento == null) {
        return false;
    }

    LocalDate hoy = LocalDate.now();

    int anioActual = hoy.getYear();
    int mesActual = hoy.getMonthValue();
    int diaActual = hoy.getDayOfMonth();

    int anioNacimiento = fechaNacimiento.getYear();
    int mesNacimiento = fechaNacimiento.getMonthValue();
    int diaNacimiento = fechaNacimiento.getDayOfMonth();

    int cantidadAnios = anioActual - anioNacimiento;

    // Si tiene más de 18 años, ya está claro que es mayor de edad
    if (cantidadAnios > 18) {
        return true;
    }

    // Si tiene menos de 18 años, directamente no es mayor
    if (cantidadAnios < 18) {
        return false;
    }

    // Si tiene exactamente 18 años, comparamos mes y día
    if (mesActual > mesNacimiento) {
        return true;
    } else if (mesActual < mesNacimiento) {
        return false;
    } else {
        // mismo mes → comparamos día
        return diaActual >= diaNacimiento;
    }
}


     public List<Postulante> buscarPorCriterio(String criterio) throws ExcepcionBiosWork {
        return respositorioPostulantes.findAll().stream()
                .filter(p -> p.getUsuario().toLowerCase().contains(criterio.toLowerCase()))
                .toList();
     }


 
//////////////////



     @Override
     @Transactional
     public void actualizarCantidad(String usuario, int cantidad) throws ExcepcionBiosWork
     {
        if (obtener(usuario)==null) {
            throw new ExcepcionNoExiste("No existe el postulante.");
        }
        respositorioPostulantes.actualizarCantidadPostulaciones(usuario, cantidad);
     }


     @Override
        public Integer obtenerCantidad(String usuario) throws ExcepcionBiosWork
        {
            if (obtener(usuario)==null) {
                throw new ExcepcionNoExiste("No existe el postulante.");
            }
            return respositorioPostulantes.obtenerCantidadPostulaciones(usuario);
        }

////////////////////////////




         @Override
    public Postulante buscar(String usuario)  throws ExcepcionBiosWork
    {
           return respositorioPostulantes
            .findByUsuario(usuario)
            .orElse(null);
    }

     @Override
     public Postulante obtener(String usuario) throws ExcepcionBiosWork {
        return respositorioPostulantes
            .findByUsuario(usuario)
            .orElse(null);
     }



}

