package com.bios.edu.uy.obligatorio2025.Repositorios;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;


import java.util.List;
import java.util.Optional;



public interface IRepositorioConsultores extends JpaRepository<Consultor,String> {
    
   
    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Consultor> findAll();


    
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional <Consultor> findByUsuario(String usuario);


}
