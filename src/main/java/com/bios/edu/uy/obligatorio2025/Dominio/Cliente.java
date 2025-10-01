package com.bios.edu.uy.obligatorio2025.Dominio;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Digits;
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
    @NotNull(message = "Ingrese el nombre.")
    private String nombre;
   
    @Pattern(regexp = "^www\\..*", message = "La URL debe empezar con www")
    @Pattern(regexp = ".*\\.com$", message = "La URL debe terminar en .com")
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

    public Cliente(String usuario, String clave,
            @NotNull(message = "Ingrese el RUT.") @Digits(integer = 12, fraction = 0, message = "El RUT debe tener 12 dígitos") Long rut,
            @NotNull(message = "Ingrese el nombre.") String nombre,
            @Pattern(regexp = "^www\\..*", message = "La URL debe empezar con www") @Pattern(regexp = ".*\\.com$", message = "La URL debe terminar en .com") String url) {
        super(usuario, clave);
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
    }

     public Cliente(){}

}
