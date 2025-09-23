package com.bios.edu.uy.obligatorio2025.Dominio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name="usuarios")
public abstract class usuarios {
    
    @Id
    @Column(length = 15)
    @NotNull (message = "ingrese el usuario")
    private String usuario;

    @Pattern(regexp = "[A-Z]")
    @Pattern(regexp = "!#$%&/()=?")
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
    public usuarios(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }


 public usuarios() {
       
    }


    @Override
    public String toString() {
        return "usuarios [usuario=" + usuario + ", clave=" + clave + "]";
    }




}
