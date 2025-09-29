package com.bios.edu.uy.obligatorio2025.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;


@Repository
public interface IRepositorioUsuarios extends JpaRepository<Usuario,String> {
        
    Usuario findByUsuario (String usuario);    
     
    Usuario findByUsuarioAndClave(String usuario, String clave);
}
