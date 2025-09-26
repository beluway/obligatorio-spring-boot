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


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public areas(@NotNull(message = "ingrese el nombre del área") String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "areas [nombre=" + nombre + "]";
    }


    


}
