package com.bios.edu.uy.obligatorio2025.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="consultores")
public class consultores extends usuarios{
    

public consultores(String usuario, String clave)
{
        super(usuario, clave);
}



}
