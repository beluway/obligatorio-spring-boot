package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;


public interface IRepositorioPostulaciones extends JpaRepository<Postulacion,Postulacion.PostulacionId> {
    
List<Postulacion> findAllByPostulante(Postulante postulante);

Postulacion findById(Integer id);

@EntityGraph(type = EntityGraphType.LOAD, attributePaths = { "oferta.id", "postulante.usuario" })
Optional<Postulacion> findByOfertaAndPostulante(Oferta oferta, Postulante postulante);

Optional<Postulacion> findById_IdOfertaAndId_UsuarioPostulante(Integer idOferta, String usuario);

}
//l
