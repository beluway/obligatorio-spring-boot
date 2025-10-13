package com.bios.edu.uy.obligatorio2025.Repositorios;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bios.edu.uy.obligatorio2025.Dominio.Consultor;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;


public interface IRepositorioPostulantes extends JpaRepository<Postulante,String>{
    
     //Usuario findByUsuario (String usuario);    

    /*  @NativeQuery
     Postulante Postulante 
 */


 //@Query("select * from usuarios u inner join postulantes p on u.usuario = p.usuario")
 

 @Query(value="select u from usuarios u inner join postulantes p where u.usuario = p.usuario",nativeQuery = true)
 Postulante buscarPostulante (@Param("usuario")String usuario);
 


    @Override
    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    List<Postulante> findAll();


    @EntityGraph(type = EntityGraphType.LOAD, attributePaths = {"roles"})
    Optional <Postulante> findByUsuario(String usuario);


}
