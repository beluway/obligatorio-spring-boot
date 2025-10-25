package com.bios.edu.uy.obligatorio2025.Servicios;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Dominio.Rol;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionYaExiste;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioConsultores;

@Service
public class ServicioConsultores implements IServicioConsultores{
    
    @Autowired
    private IRepositorioConsultores repositorioConsultores;

     @Autowired
     PasswordEncoder codificador;    


    @Override
    public void agregar (Consultor consultor) throws ExcepcionBiosWork
    {

        consultor.setActivo(true);

        Consultor existente=repositorioConsultores.findById(consultor.getUsuario()).orElse(null);

        if(existente!=null)
        {
            throw new ExcepcionYaExiste("El consultor ya existe.");
        }
        
        consultor.getRoles().add(new Rol("consultor"));
        consultor.setClave(codificador.encode(consultor.getClave()));
        repositorioConsultores.save(consultor);
    }


    @Override
    public void modificar(Consultor nuevo) throws ExcepcionBiosWork
    {
        nuevo.setActivo(true);


          //ESTE ES PARA: SACAR EL ROL, Y LA CONTRASEÑA GUARDADA EN CASO DE QUE NO SE CAMBIE LA CONTRASEÑA
          Consultor existe = repositorioConsultores.findById(nuevo.getUsuario()).orElse(null);


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
            throw new ExcepcionNoExiste("El postulante no existe");
          }

         nuevo.getRoles().clear();

         for(Rol r : existe.getRoles())
         {
            nuevo.getRoles().add(r);
         }
                  

        repositorioConsultores.save(nuevo);
    }



    @Override
    public void eliminar (String usuario) throws ExcepcionBiosWork
    {
        try {
            repositorioConsultores.delete(obtener(usuario));
        } catch (ExcepcionBiosWork e) {
            throw new ExcepcionBiosWork("No se pudo eliminar el consultor");
        }
    }



    @Override
    public List<Consultor>listaConsultores() throws ExcepcionBiosWork {
         //ArrayList<consultores> lista = new ArrayList<>();

        List<Consultor> lista = repositorioConsultores.findAll();

         return lista;
    }
    

    @Override
    public Consultor obtener(String usuario) throws ExcepcionBiosWork
    {
        Consultor consultorEncontrado = repositorioConsultores.findById(usuario).orElse(null);

        return consultorEncontrado;
    }

    @Override
        public List<Consultor> buscarPorCriterio(String criterio) throws ExcepcionBiosWork {
        return repositorioConsultores.findAll().stream()
                .filter(p -> p.getUsuario().toLowerCase().contains(criterio.toLowerCase()))
                .toList();
     }

}
