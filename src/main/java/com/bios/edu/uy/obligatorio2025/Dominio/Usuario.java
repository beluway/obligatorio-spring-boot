package com.bios.edu.uy.obligatorio2025.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_usuario")
@Table(name="usuarios")

public abstract  class Usuario {
    
    @Id
    @NotNull (message = "ingrese el usuario")
    private String usuario;
   
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[!#$%&/()=?]).{6,15}$",
    message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial")
    @Column(name = "clave", nullable = false,length = 15)
    @NotNull (message =  "ingrese la clave")
    private String clave;

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

    public Usuario(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    //USUARIO SOLO PARA PRELOGUEO
      public Usuario(String usuario) {
        this.usuario = usuario;
       
    }

    public Usuario() {
       
    }


    @Override
    public String toString() {
        return "usuarios [usuario=" + usuario + ", clave=" + clave + "]";
    }


    

}
