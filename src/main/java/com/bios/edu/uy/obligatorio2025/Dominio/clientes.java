package com.bios.edu.uy.obligatorio2025.Dominio;
import java.util.ArrayList;



import jakarta.validation.constraints.NotNull;


    
public class clientes extends usuarios {
    


    @NotNull(message = "Ingrese el RUT.")
    private long rut;

    @NotNull(message = "Ingrese el nombre.")
    private String nombre;

    
    private String url;

    @NotNull
    private ArrayList<ofertas> listaOfertas;


    public long getRut() {
        return rut;
    }
    public void setRut(long rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

   public ArrayList<ofertas> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(ArrayList<ofertas> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
      

  

    public clientes(String usuario, String clave, @NotNull(message = "Ingrese el RUT.") long rut,
            @NotNull(message = "Ingrese el nombre.") String nombre, String url,
            @NotNull ArrayList<ofertas> listaOfertas) {
        super(usuario, clave);
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
        this.listaOfertas = listaOfertas;
    }
    @Override
    public String toString() {
        return "clientes [rut=" + rut + ", nombre=" + nombre +", url= " +url +"]";
    }





}
