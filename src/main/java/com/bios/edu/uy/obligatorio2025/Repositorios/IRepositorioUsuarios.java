package com.bios.edu.uy.obligatorio2025.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.usuarios;

public interface IRepositorioUsuarios extends JpaRepository<usuarios,String>{
    
}
