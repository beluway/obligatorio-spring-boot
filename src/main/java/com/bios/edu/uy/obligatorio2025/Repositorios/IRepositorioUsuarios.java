package com.bios.edu.uy.obligatorio2025.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;

public interface IRepositorioUsuarios extends JpaRepository<Usuario,String> {
    
    @Query(value = "SELECT * FROM usuarios WHERE usuario = ?1",nativeQuery = true)
    Usuario usuarioLogueado (String usuario);
     
}
