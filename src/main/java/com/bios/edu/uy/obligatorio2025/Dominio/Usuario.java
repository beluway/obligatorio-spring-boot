package com.bios.edu.uy.obligatorio2025.Dominio;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Pattern;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
/* @DiscriminatorColumn(name="tipo_usuario") */

@Table(name="usuarios")

public abstract  class Usuario {
    
    @Id
    @NotBlank (message = "ingrese el usuario")
    private String usuario;
   
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&/()=?]).{6,15}$",
    message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial")
    @Column(name = "clave", nullable = false,length = 15)
    @NotBlank (message =  "ingrese la clave")
    private String clave;

    @ManyToMany
    @JoinTable(joinColumns = { @JoinColumn(name = "usuario_nombre_usuario") }, 
    inverseJoinColumns = { @JoinColumn(name = "rol_nombre_rol") })
    private Set<Rol> roles;

    private Boolean activo;


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
  
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

     public Set<Rol> getRoles() {
        return this.roles;
    }




    public Usuario(@NotBlank(message = "ingrese el usuario") String usuario,
            @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&/()=?]).{6,15}$", message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial") @NotBlank(message = "ingrese la clave") String clave,
            Boolean activo) {
        this.usuario = usuario;
        this.clave = clave;
        this.activo = activo;

        roles = new HashSet<>();

    }

    public Usuario(){}

 
    

}
