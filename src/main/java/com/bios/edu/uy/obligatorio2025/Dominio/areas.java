package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="areas")
public class areas {
 
    @Id
    @NotNull(message = "ingrese el nombre del área")
    private String nombre;


    private boolean asignadaAOferta;


    public boolean isAsignadaAOferta() {
        return asignadaAOferta;
    }

    public void setAsignadaAOferta(boolean asignadaAOferta) {
        this.asignadaAOferta = asignadaAOferta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public areas(@NotNull(message = "ingrese el nombre del área") String nombre, boolean asignadaAOferta) {
        this.nombre = nombre;
        this.asignadaAOferta = asignadaAOferta;
    }

    @Override
    public String toString() {
        return "areas [nombre=" + nombre + ", asignadaAOferta=" + asignadaAOferta + "]";
    }

  

   

    


}
