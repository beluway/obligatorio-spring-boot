package com.bios.edu.uy.obligatorio2025.Dominio;

import org.springframework.boot.context.properties.bind.DefaultValue;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="areas")
public class Area {
 
    @Id    
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Integer id;

    @NotBlank(message = "Ingrese el nombre del área")
    @Column(name = "nombre", unique = true)
    private String nombre;

    @Column(name = "asignada")
    private Boolean asignada =false;

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

    public Boolean getAsignada() {
        return asignada;
    }

    public void setAsignada(Boolean asignada) {
        this.asignada = asignada;
    }

    public Area(Integer id, @NotBlank(message = "ingrese el nombre del área") String nombre, Boolean asignada) {
        this.id = id;
        this.nombre = nombre;
        this.asignada = asignada;
    }

    @Override
    public String toString() {
        return "Area [id=" + id + ", nombre=" + nombre + ", asignada=" + asignada + "]";
    }

  

    

}
