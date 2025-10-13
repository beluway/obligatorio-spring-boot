package com.bios.edu.uy.obligatorio2025.Dominio;



import java.util.Set;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name="consultores")
/* @PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
@DiscriminatorValue("Consultor") */

public class Consultor extends Usuario{
    
    /*    private String tipo_usuario; */


       
      public Consultor(){}

                public Consultor(@NotBlank(message = "ingrese el usuario") String usuario,
                @NotBlank(message = "ingrese la clave") String clave, Set<Rol> roles, Boolean activo)
                {

                super(usuario, clave, roles, activo);
                
                }
/* 
        public Consultor(@NotBlank(message = "ingrese el usuario") String usuario,
            @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&/()=?]).{6,15}$", message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial") @NotBlank(message = "ingrese la clave") String clave,
            Boolean activo) {
                super(usuario, clave,activo);
              
        }
 */
    /*     public String getTipo_usuario() {
                return tipo_usuario;
        }

        public void setTipo_usuario(String tipo_usuario) {
                this.tipo_usuario = tipo_usuario;
        } */

    

}
