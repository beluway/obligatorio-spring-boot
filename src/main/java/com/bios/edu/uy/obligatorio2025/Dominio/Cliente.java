package com.bios.edu.uy.obligatorio2025.Dominio;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
@DiscriminatorValue("Cliente")

public class Cliente extends Usuario {
       
    //private String tipo_usuario;

   
    @Column(name = "rut", nullable = false, length = 12, unique = true)
    @NotNull(message = "Ingrese el RUT.")
    @Digits(integer =  12, fraction = 0, message = "El RUT debe tener 12 dígitos")    
    private Long rut;

    @Column(name = "nombre", nullable = false, length = 15)
    @NotBlank(message = "Ingrese el nombre.")
    private String nombre;
   
   @Pattern(
    regexp = "^www\\..*\\.com$",
    message = "La URL debe comenzar con www")
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

    public Cliente(@NotNull(message = "ingrese el usuario") String usuario,
            @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#$%&/()=?]).{6,15}$", message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial") @NotNull(message = "ingrese la clave") String clave,
            @NotNull(message = "Ingrese el RUT.") @Digits(integer = 12, fraction = 0, message = "El RUT debe tener 12 dígitos") Long rut,
            @NotBlank(message = "Ingrese el nombre.") String nombre,
            @Pattern(regexp = "^www\\..*\\.com$", message = "La URL debe comenzar con www") String url) {
        super(usuario, clave);
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
    }


    public Cliente(){}
   
}
