package com.bios.edu.uy.obligatorio2025.Repositorios;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;


public interface IRepositorioPostulantes extends JpaRepository<Postulante,String>{
     
 //ESTO SE USA PARA ACTUALIZAR LA CANTIDAD DE POSTULACIONES DE UN POSTULANTE
    @Modifying
    @Query ("UPDATE Postulante p SET p.cantidadPostulaciones =:cantidad WHERE p.usuario = :usuario")
    void actualizarCantidadPostulaciones (@Param("usuario") String usuario, @Param("cantidad")int cantidad);


    @Query("SELECT p.cantidadPostulaciones FROM Postulante p WHERE p.usuario = :usuario")
    Integer obtenerCantidadPostulaciones (@Param ("usuario") String usuario);


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Postulante> findAll();


    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional <Postulante> findByUsuario(String usuario);

    /* List<Postulante> findAllByOferta(Oferta oferta); */
    

}
