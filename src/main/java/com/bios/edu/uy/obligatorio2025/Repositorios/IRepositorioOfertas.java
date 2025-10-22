package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.time.LocalDate;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.lang.Nullable;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public interface IRepositorioOfertas extends JpaRepository<Oferta,Integer>, JpaSpecificationExecutor<Oferta> {

    List<Oferta> findAllByOrderByAreaAsc();

    
    List<Oferta> findAllByCliente(Cliente cliente);   


    //POSTULANTE: MIS OFERTAS
    //List<Oferta> findAllByPostulante(Postulante postulante);
   

    public static Specification<Oferta> ofertasVigentes()
    {
        return new Specification<Oferta>(){

            @Override         
            public Predicate toPredicate(Root<Oferta> root, @Nullable CriteriaQuery<?> query,
                    CriteriaBuilder filtro) {
              
             return filtro.between(
                filtro.literal(LocalDate.now()),           // valor a evaluar
                root.get("fechaPublicacion"), // límite inferior
                root.get("fechaCierre")       // límite superior
            );
            
            }
        };
        
    }

        Oferta findByCliente(Cliente cliente);


     
   
}