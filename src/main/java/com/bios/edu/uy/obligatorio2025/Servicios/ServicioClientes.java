package com.bios.edu.uy.obligatorio2025.Servicios;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.*;


@Service
public class ServicioClientes /* implements IServicioClientes */ {

  /*   private List<clientes> clientes;

    public ServicioClientes(IServicioOfertas servicioOfertas){

        clientes = new ArrayList<>();

        clientes.add(new clientes("user1", "1234", 123456789012L, "Cliente 1", null, servicioOfertas.listaOfertas())); */
  /*   } */

//AGREGAR
   public void agregar(clientes cliente) throws Exception 
   { 
      /*   if (obtener(cliente.getUsuario()) != null) {
            throw new Exception("El cliente ya existe.");
        }

        clientes.add(cliente); */
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
  public void modificar (clientes cliente) throws Exception
    { 
       /*  int posicion = obtenerPosicion(cliente.getUsuario());

        if (posicion == -1) {
            throw new Exception("El cliente no existe.");
        }

        cliente.setNombre(cliente.getNombre());
        cliente.setRut(cliente.getRut());

        clientes.set(posicion, cliente);
 */
   }

//* /ELIMINAR
   public void eliminar (String usuario) throws Exception
    { 
       /*  if (obtener(usuario)!=null) {
            clientes.remove(obtenerPosicion(usuario));
        }
        else{
            throw new Exception("El cliente no existe");
        } */

   } 

//LISTAR
     public List<clientes>listaClientes()
    {
          ArrayList<clientes> lista = new ArrayList<>();

          return lista;
    } 

//OBTENER
  /*   public clientes obtener(String usuario) {
        clientes clienteEncontrado = null;

        for(clientes c : clientes){
            if(c.getUsuario()==usuario){
                clienteEncontrado = c;
                break;
            }
        }
        return clienteEncontrado;
    } */ 

} 
