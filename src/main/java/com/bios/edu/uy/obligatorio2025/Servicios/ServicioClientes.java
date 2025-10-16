package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
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

  
    

  /*   private List<clientes> clientes;

    public ServicioClientes(IServicioOfertas servicioOfertas){

        clientes = new ArrayList<>();

        clientes.add(new clientes("user1", "1234", 123456789012L, "Cliente 1", null, servicioOfertas.listaOfertas())); */
  /*   } */

//AGREGAR
 @Override
   public void agregar(Cliente cliente) throws ExcepcionBiosWork
   { 

    Cliente clienteExiste = obtener(cliente.getUsuario());
            if (clienteExiste != null) {
                throw new ExcepcionYaExiste("El cliente ya existe.");
                
            }else if (cliente.getRut().toString().length() != 12) {
                throw new ExcepcionBiosWork("El RUT debe tener 12 digitos.");
            }

            cliente.getRoles().add(new Rol("postulante"));
            cliente.setActivo(true);
            cliente.setClave(codificador.encode(cliente.getClave()));


            repositorioClientes.save(cliente);

    } 

    //OBTENER POS
   /*  private int obtenerPosicion(String usuario) {
        int posicion = -1;

        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getUsuario() == usuario) {
                posicion = i;

                break;
            }
        }

        return posicion;

    } */
    
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
            throw new ExcepcionNoExiste("el postulante no existe");
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

       // Cliente clienteenBD = repositorioClientes.findById(usuario).orElse(null);

       Cliente clienteEnBD = repositorioClientes.findById(usuario).orElse(null);
       
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
     public List<Cliente>listaClientes()
    {
        //  ArrayList<clientes> lista = new ArrayList<>();

            List<Cliente> lista = repositorioClientes.findAll();

          return lista;
    } 
    
 @Override
    public List<Cliente> listarActivos() {
    return repositorioClientes.findByActivoTrue();
}

public boolean existePorUrl(String url) {
    return repositorioClientes.existsByUrl(url);
}

//OBTENER
    @Override
     public Cliente obtener(String usuario) {
        
        Cliente clienteEncontrado = repositorioClientes.findById(usuario).orElse(null);
           
        return clienteEncontrado; 
    } 
     
   

} 
