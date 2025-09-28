package com.bios.edu.uy.obligatorio2025.Dominio;


import jakarta.persistence.Entity;


@Entity
public class Consultor extends Usuario{
    
  
        //constructor x defecto
        public Consultor(){}

        public Consultor(String usuario, String clave)
        {
                super(usuario, clave);

        }

      
    


}
