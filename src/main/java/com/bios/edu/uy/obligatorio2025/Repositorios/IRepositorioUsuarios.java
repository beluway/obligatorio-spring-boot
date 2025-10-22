package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;


@Repository
public interface IRepositorioUsuarios extends JpaRepository<Usuario,String> {
        
    Optional<Usuario> findByUsuario(String usuario);
     
    Usuario findByUsuarioAndClave(String usuario, String clave);


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Usuario> findAll();


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional <Usuario> findById(String usuario);

    
    //PRE LOGUEO SOLO PARA USUARIOS ACTIVOS
    Optional<Usuario> findByUsuarioAndActivoTrue(String usuario);

}
