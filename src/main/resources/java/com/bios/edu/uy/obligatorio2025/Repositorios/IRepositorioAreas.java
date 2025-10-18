package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;

public interface IRepositorioAreas extends JpaRepository<Area,Integer> {

  Optional<Area> findByNombre(String nombre);
}
