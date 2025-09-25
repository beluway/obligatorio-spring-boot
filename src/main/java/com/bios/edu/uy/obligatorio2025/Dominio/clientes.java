package com.bios.edu.uy.obligatorio2025.Dominio;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class clientes extends usuarios {
       
    @Column(name = "rut", nullable = false, length = 12)
    @NotNull(message = "Ingrese el RUT.")
    @Size(min=12, max=12)
    private Long rut;

    @Column(name = "nombre", nullable = false, length = 15)
    @NotNull(message = "Ingrese el nombre.")
    private String nombre;

    //termina con .com
    @Pattern(regexp = "*.[.com]")
    //empieza con www    
    @Pattern(regexp="[www].*")
    @Column(name="url")
    private String url;

    public Long getRut() {
        return rut;
    }

    public void setRut(Long rut) {
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

  

    public clientes(String usuario, String clave, @NotNull(message = "Ingrese el RUT.") Long rut,
            @NotNull(message = "Ingrese el nombre.") String nombre, String url,
            @NotNull List<ofertas> listaOfertas) {
        super(usuario, clave);
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
      
    }


    
    @Override
    public String toString() {
        return "clientes [rut=" + rut + ", nombre=" + nombre +", url= " +url +"]";
    }





}
