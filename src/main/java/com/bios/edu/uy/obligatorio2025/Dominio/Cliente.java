package com.bios.edu.uy.obligatorio2025.Dominio;

import java.util.Set;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;




@Entity
@Table(name = "clientes")
/* @PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
@DiscriminatorValue("Cliente") */

public class Cliente extends Usuario {
       
    //private String tipo_usuario;

   
    @Column(name = "rut", nullable = false, length = 12, unique = true)
    @NotNull(message = "Ingrese el RUT.")
    @Digits(integer =  12, fraction = 0, message = "El RUT debe tener 12 dígitos.")    
    private Long rut;

    @Column(name = "nombre", nullable = false, length = 15)
    @NotBlank(message = "Ingrese el nombre.")
    private String nombre;
   
   @Pattern(
    regexp = "^www\\..*\\.com$",
    message = "La URL debe comenzar con www y debe terminar con .com, además no puede repetirse.")
    @Column(name="url", unique = true)
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


    /* public Cliente(@NotBlank(message = "ingrese el usuario") String usuario,
            @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&/()=?]).{6,15}$", message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial") @NotBlank(message = "ingrese la clave") String clave,
            Boolean activo,
            @NotNull(message = "Ingrese el RUT.") @Digits(integer = 12, fraction = 0, message = "El RUT debe tener 12 dígitos") Long rut,
            @NotBlank(message = "Ingrese el nombre.") String nombre,
            @Pattern(regexp = "^www\\..*\\.com$", message = "La URL debe comenzar con www") String url) {
        super(usuario, clave, activo);
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
    } */


    
     public Cliente(){}

     public Cliente(@NotBlank(message = "ingrese el usuario") String usuario,
            @NotBlank(message = "ingrese la clave") String clave, Set<Rol> roles, boolean activo,
            @NotNull(message = "Ingrese el RUT.") @Digits(integer = 12, fraction = 0, message = "El RUT debe tener 12 dígitos") Long rut,
            @NotBlank(message = "Ingrese el nombre.") String nombre,
            @Pattern(regexp = "^www\\..*\\.com$", message = "La URL debe comenzar con www") String url) {
        super(usuario, clave, roles, activo);
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
     }

   
}
