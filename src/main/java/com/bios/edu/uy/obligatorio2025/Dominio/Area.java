package com.bios.edu.uy.obligatorio2025.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name="areas")
public class Area {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "ingrese el nombre del área")
    private String nombre;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //constructor x defecto para JPA
    public Area(){}

    public Area(@NotNull(message = "ingrese el nombre del área") String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "areas [id=" + id + ", nombre=" + nombre + "]";
    }

    //AYUDAAAA

}
