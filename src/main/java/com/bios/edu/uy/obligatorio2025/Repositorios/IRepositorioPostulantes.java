package com.bios.edu.uy.obligatorio2025.Repositorios;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;


public interface IRepositorioPostulantes extends JpaRepository<Postulante,String>{
     


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Postulante> findAll();


    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional <Postulante> findByUsuario(String usuario);

}
