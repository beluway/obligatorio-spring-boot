package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;


public interface IRepositorioPostulaciones extends JpaRepository<Postulacion,Postulacion.PostulacionId> {
    
List<Postulacion> findAllByPostulante_Usuario(String usuario);
    
List<Postulacion> findAllByPostulante(Postulante postulante);

//SE BUSCA POR:
// OFERTA(Id_)con mayuscula y el guion bajo significa que se va a buscar en las clases embebidas segun el id de Oferta
//IdOferta (con mayuscula indica clave de la clase embebida Oferta)
//UsuarioPostulante (con mayuscula indica clave de la clase embebida Postulante)
Optional<Postulacion> findById_IdOfertaAndId_UsuarioPostulante(Integer idOferta, String usuarioPostulante);


// SE SACAN TODAS LAS POSTULACIONES POR OFERTA
//PARA HACER LA BAJA FISICA, PRIMERO SE ELIMINAN TODAS ESTAS POSTULACIONES, Y DESPUES LA OFERTA
List<Postulacion> findAllByOferta(Oferta oferta);

List<Postulacion> findByOferta(Oferta oferta);

}

