package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;


@Entity
@Table(name="consultores")
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
@DiscriminatorValue("Consultor")

public class Consultor extends Usuario{
    
    /*    private String tipo_usuario; */

        @Column(name="activo")
        private Boolean activo=true;
        

        //constructor x defecto
        public Consultor(){
                super("", "");
        }


        public Boolean getActivo() {
                return activo;
        }


        public void setActivo(Boolean activo) {
                this.activo = activo;
        }


        public Consultor(String usuario, String clave, Boolean activo) {
                super(usuario, clave);
                this.activo = activo;
        }

    /*     public String getTipo_usuario() {
                return tipo_usuario;
        }

        public void setTipo_usuario(String tipo_usuario) {
                this.tipo_usuario = tipo_usuario;
        } */

    

}
