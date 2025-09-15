package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.validation.constraints.NotNull;

public class areas {
    
    @NotNull(message = "ingrese el nombre del área")
    private String nombre;

}
