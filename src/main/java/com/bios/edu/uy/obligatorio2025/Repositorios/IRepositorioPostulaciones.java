package com.bios.edu.uy.obligatorio2025.Repositorios;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;


public interface IRepositorioPostulaciones extends JpaRepository<Postulacion,Postulacion.PostulacionId> {
    



}
