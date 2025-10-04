package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;


public interface IRepositorioPostulaciones extends JpaRepository<Postulacion,Postulacion.PostulacionId> {
    
List<Postulacion> findAllByPostulante(Postulante postulante);

//List<Postulacion> findAllbyUsuario();


}
