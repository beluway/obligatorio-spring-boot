package com.bios.edu.uy.obligatorio2025.Dominio;



import java.util.Set;


import jakarta.persistence.Entity;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;



@Entity
@Table(name="consultores")

public class Consultor extends Usuario{
    
      public Consultor(){}

                public Consultor(@NotBlank(message = "{NotBlank.usuario.usuario}") String usuario,
                @NotBlank(message = "NotBlank.usuario.clave") String clave, Set<Rol> roles, Boolean activo)
                {
                super(usuario, clave, roles, activo);
                
                }

}
