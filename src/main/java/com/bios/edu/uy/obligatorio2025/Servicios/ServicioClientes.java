package com.bios.edu.uy.obligatorio2025.Servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionYaExiste;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepostorioClientes;


@Service
public class ServicioClientes implements IServicioClientes  {


    @Autowired
    private IRepostorioClientes repositorioClientes;

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

            cliente.getRoles().add(new Rol("cliente"));

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
  public void modificar (Cliente clienteActualizado, String nuevaClave) throws ExcepcionBiosWork
    { 
         Cliente existente = obtener(clienteActualizado.getUsuario());

        //ESTO ESTA BIEN ??? 

        // Si algún campo vino vacío, conservamos el valor anterior
        if (clienteActualizado.getUsuario() == null || clienteActualizado.getUsuario().isBlank()) {
            clienteActualizado.setUsuario(existente.getUsuario());
        }
        if (clienteActualizado.getNombre() == null || clienteActualizado.getNombre().isBlank()) {
            clienteActualizado.setNombre(existente.getNombre());
        }
        if (clienteActualizado.getClave() == null || clienteActualizado.getClave().isBlank()) {
            clienteActualizado.setClave(existente.getClave());
        }
        if (clienteActualizado.getRut() == null || clienteActualizado.getRut().toString().isBlank()) {
            clienteActualizado.setRut(existente.getRut());
        }
        if (clienteActualizado.getUrl() == null || clienteActualizado.getUrl().isBlank()) {
            clienteActualizado.setUrl(existente.getUrl());
        }

        

        if (nuevaClave != null && !nuevaClave.isBlank()) {
        clienteActualizado.setClave(nuevaClave);
        } else {
        clienteActualizado.setClave(existente.getClave());
        }

        repositorioClientes.save(clienteActualizado);

   }

//* /ELIMINAR
 @Override
   public void eliminar (String usuario) throws ExcepcionBiosWork
    { 
       /*  if (obtener(usuario)!=null) {
            clientes.remove(obtenerPosicion(usuario));
        }
        else{
            throw new Exception("El cliente no existe");
        } */

        repositorioClientes.delete(obtener(usuario));
   } 

//LISTAR
 @Override
     public List<Cliente>listaClientes()
    {
        //  ArrayList<clientes> lista = new ArrayList<>();

            List<Cliente> lista = repositorioClientes.findAll();

          return lista;
    } 

//OBTENER
    @Override
     public Cliente obtener(String usuario) {
        
        Cliente clienteEncontrado = repositorioClientes.findById(usuario).orElse(null);
           
        return clienteEncontrado; 
    } 

} 
