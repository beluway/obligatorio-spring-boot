package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;

public interface IRepostorioClientes extends JpaRepository<Cliente,String> {


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Cliente> findAll();


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional <Cliente> findById(String usuario);


    List<Cliente> findByActivoTrue();

    boolean existsByUrl(String url);

  

}