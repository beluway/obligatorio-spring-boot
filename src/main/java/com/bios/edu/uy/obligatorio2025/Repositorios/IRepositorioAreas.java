package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.areas;
import com.bios.edu.uy.obligatorio2025.Dominio.ofertas;

public interface IRepositorioAreas extends JpaRepository<areas,String> {

    areas findByNombre(String nombre);
}
