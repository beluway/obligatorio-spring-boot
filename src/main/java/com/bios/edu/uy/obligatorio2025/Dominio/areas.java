package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.validation.constraints.NotNull;

public class areas {
    
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public areas(Integer id, @NotNull(message = "ingrese el nombre del área") String nombre, boolean asignadaAOferta) {
        this.id = id;
        this.nombre = nombre;
        this.asignadaAOferta = asignadaAOferta;
    }

    @Override
    public String toString() {
        return "areas [id=" + id + ", nombre=" + nombre + ", asignadaAOferta=" + asignadaAOferta + "]";
    }

   

    


}
