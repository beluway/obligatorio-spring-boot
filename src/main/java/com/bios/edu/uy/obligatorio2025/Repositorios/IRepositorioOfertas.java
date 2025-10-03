package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface IRepositorioOfertas extends JpaRepository<Oferta,Integer> {

    List<Oferta> findAllByOrderByAreaAsc();
    List<Oferta> findAllByCliente(Cliente cliente);
    //List<Oferta> findByStartDateBetween(Date fechaActual,Date fechaCierreOferta);

    @Query(value = "SELECT * FROM ofertas o WHERE o.fecha_cierre>=CURRENT_DATE",nativeQuery=true)
    List<Oferta> ofertasVigentes();

    @Query(value = "SELECT COUNT(*)  FROM ofertas o INNER JOIN postulaciones p1 ON p1.oferta = o.id INNER JOIN postulantes p2 ON p2.usuario = p1.postulante  WHERE p2.usuario = ?1 AND o.fechaCierre > CURRENT_DATE",nativeQuery = true)
    Integer cantidadOfertasVencidasPorUsuario(String usuario);


}
