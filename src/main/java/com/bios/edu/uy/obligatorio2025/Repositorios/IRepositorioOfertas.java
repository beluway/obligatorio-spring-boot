package com.bios.edu.uy.obligatorio2025.Repositorios;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
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
    

    public static Specification <Oferta> ofertasVigentes(Date fechaCierrePublicación, Oferta oferta)
    {
       
        if(fechaCierrePublicación==null)
        {
            return null;
        }

        return new Specification<Oferta>()
        {

            @Override
            @Nullable
            public Predicate toPredicate(Root<Oferta> root, @Nullable CriteriaQuery<?> query,
                    CriteriaBuilder criteriaBuilder) {
                
                criteriaBuilder.between(oferta,new LocalDate().now(),fechaCierrePublicación);
            }
           

        }

        
    } 


}
