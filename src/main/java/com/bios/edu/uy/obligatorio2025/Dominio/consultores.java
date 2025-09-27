package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.persistence.Entity;


@Entity
public class consultores extends usuarios{
    
  
        //constructor x defecto
        public consultores(){}

        public consultores(String usuario, String clave)
        {
                super(usuario, clave);

        }

      
    


}
