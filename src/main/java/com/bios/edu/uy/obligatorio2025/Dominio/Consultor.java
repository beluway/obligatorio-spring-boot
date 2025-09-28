package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name="consultores")
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
public class Consultor extends Usuario{
    
       private String tipo_usuario;

        //constructor x defecto
        public Consultor(){}

        public String getTipo_usuario() {
                return tipo_usuario;
        }

        public void setTipo_usuario(String tipo_usuario) {
                this.tipo_usuario = tipo_usuario;
        }

        public Consultor(String usuario, String clave, String tipo_usuario) {
                super(usuario, clave);
                this.tipo_usuario = tipo_usuario;
        }
    


}
