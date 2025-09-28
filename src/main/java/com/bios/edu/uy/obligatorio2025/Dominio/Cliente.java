package com.bios.edu.uy.obligatorio2025.Dominio;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
public class Cliente extends Usuario {
       
    @Column(name = "rut", nullable = false, length = 12)
    @NotNull(message = "Ingrese el RUT.")
    @Digits(integer =  8, fraction = 0, message = "El RUT debe tener hasta 8 dígitos")
    private Long rut;

    @Column(name = "nombre", nullable = false, length = 15)
    @NotNull(message = "Ingrese el nombre.")
    private String nombre;

   
    @Pattern(regexp = "^www\\..*", message = "La URL debe empezar con www")
    @Pattern(regexp = ".*\\.com$", message = "La URL debe terminar en .com")
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

  
    //constructor x defecto para JPA
    public Cliente(){}

    public Cliente(String usuario, String clave, @NotNull(message = "Ingrese el RUT.") Long rut,
            @NotNull(message = "Ingrese el nombre.") String nombre, String url,
            @NotNull List<Oferta> listaOfertas) {
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
