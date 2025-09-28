package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.Area;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;

public interface IRepositorioAreas extends JpaRepository<Area,String> {

}
