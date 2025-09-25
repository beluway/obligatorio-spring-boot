package com.bios.edu.uy.obligatorio2025.Dominio;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class consultores extends usuarios{
    
  

        public consultores(String usuario, String clave)
        {
                super(usuario, clave);

        }

      
    


}
