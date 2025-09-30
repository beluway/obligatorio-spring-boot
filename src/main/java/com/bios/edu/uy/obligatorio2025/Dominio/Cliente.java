package com.bios.edu.uy.obligatorio2025.Dominio;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
@DiscriminatorValue("Cliente")

public class Cliente extends Usuario {
       
    //private String tipo_usuario;

    @Column(name = "rut", nullable = false, length = 12)
    @NotNull(message = "Ingrese el RUT.")
    @Digits(integer =  12, fraction = 0, message = "El RUT debe tener 12 dígitos")
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

   /*  public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    } */
  
    //constructor x defecto para JPA
    public Cliente(){}

    public Cliente(/* String tipo_usuario, */
            @NotNull(message = "Ingrese el RUT.") @Digits(integer = 12, fraction = 0, message = "El RUT debe tener 12 dígitos") Long rut,
            @NotNull(message = "Ingrese el nombre.") String nombre,
            @Pattern(regexp = "^www\\..*", message = "La URL debe empezar con www") @Pattern(regexp = ".*\\.com$", message = "La URL debe terminar en .com") String url) {
      /*   this.tipo_usuario = tipo_usuario; */
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
    }

   

    

    





}
