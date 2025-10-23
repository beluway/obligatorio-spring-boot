package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionYaExiste;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioOfertas;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepostorioClientes;


@Service
public class ServicioClientes implements IServicioClientes  {


    @Autowired
    private IRepostorioClientes repositorioClientes;

    @Autowired
    private IRepositorioOfertas repositorioOfertas;

    @Autowired
    PasswordEncoder codificador;

//AGREGAR
 @Override
   public void agregar(Cliente cliente) throws ExcepcionBiosWork
   { 

    Cliente clienteExiste = obtener(cliente.getUsuario());
            if (clienteExiste != null) {
                throw new ExcepcionYaExiste("El cliente ya existe.");
                
            }
            if (cliente.getRut()!=null) {
                if ( cliente.getRut()!=null&&cliente.getRut().toString().length() != 12 ) {
                throw new ExcepcionBiosWork("El RUT debe tener 12 digitos.");
            }
            }
            
            cliente.getRoles().add(new Rol("postulante"));
            cliente.setActivo(true);
            cliente.setClave(codificador.encode(cliente.getClave()));


            repositorioClientes.save(cliente);

    } 

    
//MODIFICAR
  @Override 
    public void modificar (Cliente  nuevo) throws ExcepcionBiosWork
    {

          nuevo.setActivo(true);


          //ESTE ES PARA: SACAR EL ROL, Y LA CONTRASEÑA GUARDADA EN CASO DE QUE NO SE CAMBIE LA CONTRASEÑA
          Cliente existe = repositorioClientes.findById(nuevo.getUsuario()).orElse(null);


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
                  

        repositorioClientes.save(nuevo);
    }


   

//* /ELIMINAR
 @Override
   public void eliminar (String usuario) throws ExcepcionBiosWork
    { 

       Cliente clienteEnBD = repositorioClientes.findById(usuario).orElse(null);

       if (clienteEnBD==null) {
        throw new ExcepcionNoExiste("El cliente no existe.");
       }
       
       List<Oferta> ofertasDelCliente = repositorioOfertas.findAllByCliente(clienteEnBD);
       
       if(ofertasDelCliente.size()>0)
       {
            clienteEnBD.setActivo(false);

            repositorioClientes.save(clienteEnBD);
       }
       
       else
       {
        repositorioClientes.delete(clienteEnBD);
       }  
       
   } 

//LISTAR
 @Override
     public List<Cliente>listaClientes() throws ExcepcionBiosWork
    {
            List<Cliente> lista = repositorioClientes.findAll();

          return lista;
    } 
    
 @Override
    public List<Cliente> listarActivos() throws ExcepcionBiosWork {
    return repositorioClientes.findByActivoTrue();
}

public boolean existePorUrl(String url) {
    if (url == null || url.isBlank()) return false;
    return repositorioClientes.existsByUrl(url);
}

//OBTENER
    @Override
     public Cliente obtener(String usuario) throws ExcepcionBiosWork {
        
        Cliente clienteEncontrado = repositorioClientes.findById(usuario).orElse(null);
           
        return clienteEncontrado; 
    }


    @Override
    public List<Cliente> buscarPorCriterio(String criterio) {
             
        return repositorioClientes.findAll().stream()
                .filter(c -> c.getNombre().toLowerCase().contains(criterio.toLowerCase()))
                .toList();
    } 
     
   

} 
