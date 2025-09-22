package com.bios.edu.uy.obligatorio2025.Dominio;



import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="usuarios")
public abstract class usuarios {
    
     @NotNull (message = "ingrese el usuario")
    private String usuario;

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
