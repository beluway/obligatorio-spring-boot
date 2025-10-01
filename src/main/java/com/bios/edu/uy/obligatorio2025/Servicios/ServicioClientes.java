package com.bios.edu.uy.obligatorio2025.Servicios;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
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
  public void modificar (Cliente cliente) throws ExcepcionBiosWork
    { 
       /*  int posicion = obtenerPosicion(cliente.getUsuario());

        if (posicion == -1) {
            throw new Exception("El cliente no existe.");
        }

        cliente.setNombre(cliente.getNombre());
        cliente.setRut(cliente.getRut());

        clientes.set(posicion, cliente);
 */
        repositorioClientes.save(obtener(cliente.getUsuario()));

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
